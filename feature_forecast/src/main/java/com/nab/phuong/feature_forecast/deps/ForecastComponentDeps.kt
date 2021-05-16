package com.nab.phuong.feature_forecast.deps

import com.nab.phuong.weather.presentation.ForecastFragment

interface PresentationComponentDeps {
    fun inject(forecastFragment: ForecastFragment)
}
