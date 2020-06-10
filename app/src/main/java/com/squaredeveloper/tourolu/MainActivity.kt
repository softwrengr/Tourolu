package com.squaredeveloper.tourolu

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        loadWebview(Constant.url)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadWebview(Constant.url)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_flight -> {
                    loadWebview(Constant.flights)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_hotel -> {
                    loadWebview(Constant.hotels)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_villas -> {
                    loadWebview(Constant.villas)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_car -> {
                    loadWebview(Constant.car)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadWebview(webViewUrl: String) {


        // Enable and setup web view cache
        webview.settings.setAppCacheEnabled(true)
        webview.settings.cacheMode = WebSettings.LOAD_DEFAULT
        webview.settings.setAppCachePath(cacheDir.path)

        webview.settings.javaScriptEnabled = true
        webview.settings.loadWithOverviewMode = true
        webview.settings.useWideViewPort = true
        webview.settings.domStorageEnabled = true
        webview.settings.javaScriptCanOpenWindowsAutomatically = true
        webview.fitsSystemWindows = true

        if (!Utilities.isNetworkConnected(this)) {
            showToast()
            tv_nointernet.visibility = View.VISIBLE
        } else {
            tv_nointernet.visibility = View.GONE
            webview.loadUrl(webViewUrl)
        }


        webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                // Page loading started
                progressbar.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)

            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                if (!Utilities.isNetworkConnected(this@MainActivity)) {
                    tv_nointernet.visibility = View.VISIBLE
                }
                view.loadUrl(url)

                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                // Page loading finished
                progressbar.visibility = View.GONE
                super.onPageFinished(view, url)
            }
        }
    }

    fun showToast() {
        Toast.makeText(this, "No active internet connection!", Toast.LENGTH_SHORT).show()
    }

    // Handle back button press in web view
    override fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            finish()
        }
    }
}