package com.cst.kotlinfinal.forecastObjects

import com.google.gson.annotations.SerializedName

data class CloudsObject (
    @SerializedName("all") val all : Int
)