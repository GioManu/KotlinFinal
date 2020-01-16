package com.cst.kotlinfinal.forecastObjects

import com.google.gson.annotations.SerializedName
import java.util.*

data class CityObject(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("coord") val coord : CoordObject,
    @SerializedName("country") val country : String,
    @SerializedName("population") val population : Int,
    @SerializedName("timezone") val timeZone : Int,
    @SerializedName("sunrise") val sunrise : Long,
    @SerializedName("sunset") val sunset : Long
)