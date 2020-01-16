package com.cst.kotlinfinal.forecastObjects

import com.google.gson.annotations.SerializedName

data class CoordObject(
    @SerializedName("lat") val lat : Double,
    @SerializedName("lon") val lon : Double
)