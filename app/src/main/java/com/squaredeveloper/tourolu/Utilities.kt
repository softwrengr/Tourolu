package com.squaredeveloper.tourolu

import android.content.Context
import android.net.ConnectivityManager

class Utilities {
    companion object{

        fun isNetworkConnected(context: Context): Boolean {
            val connectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                ?: return false
            val activeNetworkInfo = connectivityManager
                .activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    }
}