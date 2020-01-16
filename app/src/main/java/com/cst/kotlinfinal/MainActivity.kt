package com.cst.kotlinfinal

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.cst.kotlinfinal.forecastObjects.ResponseObject
import com.cst.kotlinfinal.fragments.FragmentMain
import com.cst.kotlinfinal.fragments.FragmentWeatherHours
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL



class MainActivity : AppCompatActivity() {

    val apiUrl : String = "https://api.openweathermap.org/data/2.5/forecast?q=Tbilisi&units=metric&APPID=baf2aed056472b3da6f87e7c0400c4be"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TryRequest()
    }

    inner class WeatherAPI() : AsyncTask<String, Any, String>() {

        override fun doInBackground(vararg params: String?): String {
            runOnUiThread {
                progressBar.visibility = View.VISIBLE
            }
            val url = URL(params[0])
            val urlConnection = url.openConnection()

            val stream = BufferedInputStream(urlConnection.inputStream)
            val bufferedReader = BufferedReader(InputStreamReader(stream))
            val builder = StringBuilder()

            var chunk = bufferedReader.readLine()
            while (chunk != null) {
                builder.append(chunk)
                chunk = bufferedReader.readLine()
            }

            return builder.toString()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            result?.let {
                val gson = Gson()
                try {
                    val objs = gson.fromJson(it, ResponseObject::class.java)

                    FragmentMain.createInstance(objs.list[0]).run {
                        addFragment(R.id.main_header_fragment, this)
                    }

                    FragmentWeatherHours.createInstance(ArrayList(objs.list.take(10))).run {
                        addFragment(R.id.main_weather_hours_fragment,this)
                    }

                }catch(e:Exception) {
                    print(e.message)
                }

            }
            progressBar.visibility = View.GONE
        }
    }

    fun TryRequest() {
        doAsync {
            if(this@MainActivity.isConnectedToNetwork()) {
                val weatherAPI = WeatherAPI()
                weatherAPI.execute(apiUrl)
            }else {
                uiThread {
                    //Update the UI thread here
                    alert("No Internet Connection, Retry ?", "Alert") {
                        yesButton {
                            TryRequest()
                        }
                        noButton {
                            finishAndRemoveTask()
                        }
                    }.show()
                }
            }
        }
    }

}
