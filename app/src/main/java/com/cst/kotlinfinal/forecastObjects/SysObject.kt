package com.cst.kotlinfinal.forecastObjects

import com.google.gson.annotations.SerializedName

data class SysObject(
    @SerializedName("pod") val pod : String
)