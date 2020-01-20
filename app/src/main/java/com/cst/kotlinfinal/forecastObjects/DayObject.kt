package com.cst.kotlinfinal.forecastObjects

import java.io.Serializable

data class DayObject(
    val day_date : String,
    val day_img : String,
    val night_img : String,
    val day_temp : Int,
    val night_temp : Int
) : Serializable