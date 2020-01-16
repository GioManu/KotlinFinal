package com.cst.kotlinfinal.forecastObjects

import com.google.gson.annotations.SerializedName

data class ResponseObject (
    @SerializedName("cod") val code : String,
    @SerializedName("message") val message : Int,
    @SerializedName("cnt") val cnt : Int,
    @SerializedName("list") val list : List<ForecastElement>,
    @SerializedName("city") val city : CityObject
)