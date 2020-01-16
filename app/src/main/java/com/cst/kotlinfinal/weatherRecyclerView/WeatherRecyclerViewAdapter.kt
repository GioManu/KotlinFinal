package com.cst.kotlinfinal.weatherRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cst.kotlinfinal.R
import com.cst.kotlinfinal.forecastObjects.ForecastElement

class WeatherRecyclerViewAdapter(private val forecasts : List<ForecastElement>) : RecyclerView.Adapter<WeatherRecyclerViewHolder>() {

    override fun onBindViewHolder(holder: WeatherRecyclerViewHolder, position: Int) {
        holder.setForecast(forecasts[position])
    }

    override fun getItemCount(): Int {
        return forecasts.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherRecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_weather_hour, parent, false)
        return WeatherRecyclerViewHolder(view)
    }
}