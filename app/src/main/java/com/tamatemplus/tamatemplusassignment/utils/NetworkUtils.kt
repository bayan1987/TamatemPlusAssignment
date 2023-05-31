package com.tamatemplus.tamatemplusassignment.utils

import android.content.Context
import android.net.ConnectivityManager
import com.tamatemplus.tamatemplusassignment.App

object NetworkUtils {

    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            App.appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null
    }
}