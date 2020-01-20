package com.cst.kotlinfinal.daysRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cst.kotlinfinal.R
import com.cst.kotlinfinal.forecastObjects.DayObject
import com.cst.kotlinfinal.forecastObjects.ForecastElement

class DaysRecyclerViewAdapter(private val forecasts : List<DayObject>) : RecyclerView.Adapter<DaysRecyclerViewHolder>() {

    override fun onBindViewHolder(holder: DaysRecyclerViewHolder, position: Int) {
        holder.setDay(forecasts[position])
    }

    override fun getItemCount(): Int {
        return forecasts.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysRecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_weather_day, parent, false)
        return DaysRecyclerViewHolder(view)
    }
}