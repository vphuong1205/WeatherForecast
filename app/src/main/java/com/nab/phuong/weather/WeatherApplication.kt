package com.nab.phuong.weather

import com.nab.phuong.weather.deps.DaggerDepsHolderImpl
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApplication : DaggerDepsHolderImpl() {

    override fun onCreate() {
        super.onCreate()
        createDaggerComponents()
    }
}
