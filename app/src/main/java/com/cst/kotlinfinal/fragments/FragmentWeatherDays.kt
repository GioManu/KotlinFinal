package com.cst.kotlinfinal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cst.kotlinfinal.ImageNameForWeatherAPI
import com.cst.kotlinfinal.R
import com.cst.kotlinfinal.daysRecyclerView.DaysRecyclerViewAdapter
import com.cst.kotlinfinal.forecastObjects.DayObject
import com.cst.kotlinfinal.forecastObjects.ForecastElement
import com.soywiz.klock.Date
import com.soywiz.klock.DateFormat
import com.soywiz.klock.parse

class FragmentWeatherDays : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_days, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rc = view.findViewById(R.id.weather_days_recycler) as RecyclerView

        val adapter = DaysRecyclerViewAdapter(arguments!!.getSerializable(ARG_FORECAST_LIST) as ArrayList<DayObject>)

        rc.adapter = adapter

        rc.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

    }

    companion object {

        private const val ARG_FORECAST_LIST = "ARG_FORECAST_LIST"

        fun createInstance(forecastList : ArrayList<List<ForecastElement>>): FragmentWeatherDays {

            val fragment = FragmentWeatherDays()
            val bundle = Bundle()

            val objs = ArrayList<DayObject>()

            for (list in forecastList) {

                objs.add(

                   DayObject(
                       day_date = DateFormat("yyyy-MM-dd HH:mm:ss").parse(list[0].dateText).format("MMMM d"),
                       day_img = list[1].weather[0].icon.ImageNameForWeatherAPI(),
                       day_temp = list[1].main.tempMax.toInt(),
                       night_temp = list[0].main.tempMax.toInt(),
                       night_img = list[0].weather[0].icon.ImageNameForWeatherAPI()
                   )
               )

            }

            bundle.putSerializable(ARG_FORECAST_LIST,objs)
            fragment.arguments = bundle
            return fragment

        }
    }

}