package com.nab.phuong.weather

import com.nab.phuong.weather.deps.DaggerDepsHolderImpl

class WeatherApplication : DaggerDepsHolderImpl() {

    override fun onCreate() {
        super.onCreate()
        createDaggerComponents()
    }
}
