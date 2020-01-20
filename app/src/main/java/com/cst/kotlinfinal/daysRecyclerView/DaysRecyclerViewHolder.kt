package com.cst.kotlinfinal.daysRecyclerView

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cst.kotlinfinal.ImageNameForWeatherAPI
import com.cst.kotlinfinal.R
import com.cst.kotlinfinal.forecastObjects.DayObject
import com.cst.kotlinfinal.forecastObjects.ForecastElement
import com.soywiz.klock.DateFormat
import com.soywiz.klock.parse

class DaysRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val dt = view.findViewById<TextView>(R.id.day_date);
    val day_img = view.findViewById<ImageView>(R.id.day_img)
    val day_temp = view.findViewById<TextView>(R.id.day_temp)

    val night_img = view.findViewById<ImageView>(R.id.night_img)
    val night_temp = view.findViewById<TextView>(R.id.night_temp)

    val degree = "°c"

    fun setDay(forecast: DayObject) {

        dt.setText(forecast.day_date)
        day_temp.setText(String.format("%s%s",forecast.day_temp,degree))
        night_temp.setText(String.format("%s%s",forecast.night_temp,degree))
        Glide.with(itemView.context).load("http://openweathermap.org/img/wn/${forecast.day_img}").into(day_img)
        Glide.with(itemView.context).load("http://openweathermap.org/img/wn/${forecast.night_img}").into(night_img)

    }

}