package com.nab.phuong.weather.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nab.phuong.weather.databinding.ForecastActivityBinding

class ForecastActivity : AppCompatActivity() {

    private lateinit var binding: ForecastActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ForecastActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
