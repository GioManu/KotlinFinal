package com.cst.kotlinfinal

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.lang.StringBuilder

fun AppCompatActivity.addFragment(@IdRes fragmentContainerRes: Int, fragment: Fragment) {
    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
    transaction.add(fragmentContainerRes, fragment)
    transaction.commit()
}

fun String.ImageNameForWeatherAPI():String {

    val stringBuild = StringBuilder()
    stringBuild.insert(0,this)
    stringBuild.insert(3,"@2x.png")
    return stringBuild.toString()

}

@SuppressLint("ServiceCast")
fun Context.isConnectedToNetwork(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting() ?: false
}