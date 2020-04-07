package com.cts.telstrapoc.util

import android.content.Context
import android.net.ConnectivityManager

class AppUtil {
    companion object {
        /**
         * Check internet connection availble or not
         */
        fun isOnline(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}