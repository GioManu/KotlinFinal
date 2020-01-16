package com.cst.kotlinfinal.forecastObjects

import com.google.gson.annotations.SerializedName

data class WindObject(
    @SerializedName("speed") val speed : Double,
    @SerializedName("deg") val deg : Int
)