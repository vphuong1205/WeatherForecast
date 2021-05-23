package com.nab.phuong.feature_forecast.deps

interface ForecastDepsProvider {

    fun providesForecastComponentDeps(): ForecastComponentDeps
}
