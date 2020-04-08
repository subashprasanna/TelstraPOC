package com.cts.telstrapoc.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import java.net.HttpURLConnection
import java.net.URL

@Suppress("DEPRECATION")
class AppUtil {
    companion object {
        fun isOnline(context: Context?): Boolean {
            var isWifiConnected: Boolean = false
            var isMobileConnected: Boolean = false

            val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeInfo?.isConnected == true) {
                isWifiConnected = activeInfo.type == ConnectivityManager.TYPE_WIFI
                isMobileConnected = activeInfo.type == ConnectivityManager.TYPE_MOBILE
            }

            return (isWifiConnected || isMobileConnected)
        }

        fun checkURLReachable() : Boolean {
            try {
                val url = URL(TEST_URL)
                val httpURLConnection = url.openConnection() as HttpURLConnection
                val code = httpURLConnection.responseCode
                return if(code == 200) true else false
            } catch (e: Exception) {
                return false
            }

        }
    }
}