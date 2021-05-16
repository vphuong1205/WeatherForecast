package com.nab.phuong.weather.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nab.phuong.weather.R

class ForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forecast_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ForecastFragment.newInstance())
                .commitNow()
        }
    }
}
