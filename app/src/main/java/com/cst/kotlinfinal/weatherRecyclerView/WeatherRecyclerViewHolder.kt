package com.cst.kotlinfinal.weatherRecyclerView

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cst.kotlinfinal.ImageNameForWeatherAPI
import com.cst.kotlinfinal.R
import com.cst.kotlinfinal.forecastObjects.ForecastElement
import kotlin.math.roundToInt

class WeatherRecyclerViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val weather_temperature = view.findViewById<TextView>(R.id.fragment_hour_weather_temp)
    private val weather_time = view.findViewById<TextView>(R.id.fragment_hour_weather_time)
    private val weather_img = view.findViewById<ImageView>(R.id.fragment_hour_weather_img)
    val degree = "°c"

    @SuppressLint("SetTextI18n")
    fun setForecast(forecast: ForecastElement) {
        weather_temperature.setText(forecast.main.tempMax.roundToInt().toString() + degree)
        weather_time.setText(forecast.unixTime.toString()) // TODO : Convert UnixTimeStamp To Hours AM/PM
        Glide.with(itemView.context).load("http://openweathermap.org/img/wn/${forecast.weather[0].icon.ImageNameForWeatherAPI()}").into(weather_img)
    }

}