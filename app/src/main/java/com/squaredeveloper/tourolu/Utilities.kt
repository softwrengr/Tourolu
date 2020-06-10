package com.squaredeveloper.tourolu

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar

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


        fun customProgressBar(activity: Activity): AlertDialog? {
            val dialogBuilder =
                AlertDialog.Builder(activity)
            val inflater = activity.layoutInflater
            val dialogView: View = inflater.inflate(R.layout.custom_progressbar, null)
            dialogBuilder.setView(dialogView)
            val pd = dialogView.findViewById<ProgressBar>(R.id.indeterminateBar)
            val alertDialog = dialogBuilder.create()
            alertDialog.setCancelable(false)
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(alertDialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            lp.gravity = Gravity.CENTER
            alertDialog.window!!.setBackgroundDrawableResource(R.drawable.progress_dialoge)
            alertDialog.window!!.attributes = lp
            pd.visibility = View.VISIBLE
            return alertDialog
        }

    }
}