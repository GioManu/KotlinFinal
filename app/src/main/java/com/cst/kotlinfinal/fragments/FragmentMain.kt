package com.cst.kotlinfinal.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.cst.kotlinfinal.ImageNameForWeatherAPI
import com.cst.kotlinfinal.R
import com.cst.kotlinfinal.forecastObjects.ForecastElement
import org.w3c.dom.Text
import kotlin.math.roundToInt

class FragmentMain : Fragment() {
    private lateinit var frgmt: Layout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val degree = "°c"

        val weather_icon =view.findViewById<ImageView>(R.id.main_weatherImg)
        val weather_desc = view.findViewById<TextView>(R.id.main_weather_desc)
        val weather_temp = view.findViewById<TextView>(R.id.main_temperature)
        val weather_city = view.findViewById<TextView>(R.id.main_cityName)

        val forecast = arguments!!.getSerializable(ARG_FORECAST) as ForecastElement

        weather_desc.setText(forecast.weather[0].description)
        weather_temp.setText(forecast.main.tempMax.roundToInt().toString() + degree)
        weather_city.setText("Tbilisi, Georgia") // TODO : make dynamic ??

        Glide.with(view.context).load("http://openweathermap.org/img/wn/${forecast.weather[0].icon.ImageNameForWeatherAPI()}").into(weather_icon)

    }

    companion object {
        private val ARG_FORECAST = "FORECAST"

        fun createInstance(forecast : ForecastElement): FragmentMain {
            val fragment = FragmentMain()
            val bundle = Bundle()
            bundle.putSerializable(ARG_FORECAST,forecast)
            fragment.arguments = bundle
            return fragment
        }
    }

}