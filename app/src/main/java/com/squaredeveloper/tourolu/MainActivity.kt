package com.squaredeveloper.tourolu

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        loadWebview(Constant.url)

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
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
            R.id.navigation_visa -> {
                loadWebview(Constant.visa)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tours -> {
                loadWebview(Constant.visa)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun loadWebview(url:String) {
        val settings = webview.settings
        webview.loadUrl(url)
        settings.javaScriptEnabled = true

        // Enable and setup web view cache
        settings.setAppCacheEnabled(true)
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.setAppCachePath(cacheDir.path)

        settings.loadsImagesAutomatically = true

        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.mediaPlaybackRequiresUserGesture = false
        settings.domStorageEnabled = true
        settings.setSupportMultipleWindows(true)
        settings.loadWithOverviewMode = true
        settings.allowContentAccess = true
        settings.setGeolocationEnabled(true)
        settings.allowUniversalAccessFromFileURLs = true
        settings.allowFileAccess = true
        webview.fitsSystemWindows = true


        webview.webViewClient = object: WebViewClient(){
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                // Page loading started
               progressbar.visibility = View.VISIBLE

            }

            override fun onPageFinished(view: WebView, url: String) {
                // Page loading finished
                progressbar.visibility = View.GONE
            }
        }

        webview.webChromeClient = object: WebChromeClient(){
            override fun onProgressChanged(view: WebView, newProgress: Int) {
               // progress_bar.progress = newProgress

            }
        }
    }

    // Handle back button press in web view
    override fun onBackPressed() {
        if (webview.canGoBack()) {
            // If web view have back history, then go to the web view back history
            webview.goBack()
        } else {
            finish()
        }
    }
}