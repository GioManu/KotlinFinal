package com.cst.kotlinfinal.forecastObjects

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ForecastElement(
    @SerializedName("dt") val unixTime : Long,
    @SerializedName("main") val main : ForecastElementChildMain,
    @SerializedName("weather") val weather : ArrayList<WeatherObject>,
    @SerializedName("clouds") val clouds : CloudsObject,
    @SerializedName("wind") val wind : WindObject,
    @SerializedName("sys") val sys : SysObject,
    @SerializedName("dt_txt") val dateText : String
) : Serializable