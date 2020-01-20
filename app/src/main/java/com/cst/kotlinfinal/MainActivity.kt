package com.cst.kotlinfinal

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cst.kotlinfinal.forecastObjects.ForecastElement
import com.cst.kotlinfinal.forecastObjects.ResponseObject
import com.cst.kotlinfinal.fragments.FragmentMain
import com.cst.kotlinfinal.fragments.FragmentWeatherDays
import com.cst.kotlinfinal.fragments.FragmentWeatherHours
import com.google.gson.Gson
import com.soywiz.klock.Date
import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime
import com.soywiz.klock.parse
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private val apiUrl : String = "https://api.openweathermap.org/data/2.5/forecast?q=Tbilisi&units=metric&APPID=baf2aed056472b3da6f87e7c0400c4be"

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

                    val chunks_days = GetDaysChunksFromList(objs.list)
                    val today_forecast = GetTodayForecastFromList(objs.list)

                    FragmentWeatherDays.createInstance(chunks_days).run {
                        addFragment(R.id.main_weather_days_fragment,this)
                    }

                    FragmentMain.createInstance(today_forecast[0]).run {
                        addFragment(R.id.main_header_fragment, this)
                    }

                    FragmentWeatherHours.createInstance(ArrayList(today_forecast)).run {
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

    fun GetDaysChunksFromList(list : List<ForecastElement>) : ArrayList<List<ForecastElement>> {
        val filtered_days_list = list.filter {
            DateFormat("yyyy-MM-dd HH:mm:ss")
                .parse(it.dateText).format("HH:mm") == "12:00"
                    ||
                    DateFormat("yyyy-MM-dd HH:mm:ss")
                        .parse(it.dateText).format("HH:mm") == "03:00"
        }.sortedBy {
            DateFormat("yyyy-MM-dd HH:mm:ss")
                .parse(it.dateText).format("d HH:mm")
        }

        return ArrayList(filtered_days_list.chunked(2).drop(1))
    }

    fun GetTodayForecastFromList(list:List<ForecastElement>) : ArrayList<ForecastElement> {
        val tday = DateTime.nowLocal().format("yyyy-MM-dd")

        val tday_list = list.filter {
            DateFormat("yyyy-MM-dd HH:mm:ss")
                .parse(it.dateText).format("yyyy-MM-dd") == tday
        }.sortedBy {
            DateFormat("yyyy-MM-dd HH:mm:ss")
                .parse(it.dateText).format("HH:mm")
        }
        return ArrayList(tday_list)
    }
}
