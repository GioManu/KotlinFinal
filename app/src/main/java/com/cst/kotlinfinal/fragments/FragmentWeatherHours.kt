package com.cst.kotlinfinal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cst.kotlinfinal.R
import com.cst.kotlinfinal.forecastObjects.ForecastElement
import com.cst.kotlinfinal.weatherRecyclerView.WeatherRecyclerViewAdapter

class FragmentWeatherHours : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_hours, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rc = view.findViewById(R.id.weather_hours_recycler) as RecyclerView

        val adapter = WeatherRecyclerViewAdapter(arguments!!.getSerializable(ARG_FORECAST_LIST) as ArrayList<ForecastElement>)

        rc.adapter = adapter

        rc.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

    }

    companion object {
        private const val ARG_FORECAST_LIST = "ARG_FORECAST_LIST"

        fun createInstance(forecastList : ArrayList<ForecastElement>): FragmentWeatherHours {

            val fragment = FragmentWeatherHours()

            val bundle = Bundle()
            bundle.putSerializable(ARG_FORECAST_LIST,forecastList)
            fragment.arguments = bundle
            return fragment

        }


    }

}
