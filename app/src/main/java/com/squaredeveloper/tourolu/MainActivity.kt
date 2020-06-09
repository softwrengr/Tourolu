package com.squaredeveloper.tourolu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
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
        }
        false
    }

    private fun loadWebview(url:String) {
        webview.loadUrl(url)
    }

}