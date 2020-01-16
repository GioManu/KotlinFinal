package com.cst.kotlinfinal.forecastObjects

import com.google.gson.annotations.SerializedName

data class WeatherObject(
    @SerializedName("id") val id :Int,
    @SerializedName("main") val main :String,
    @SerializedName("description") val description : String,
    @SerializedName("icon") val icon :String
)