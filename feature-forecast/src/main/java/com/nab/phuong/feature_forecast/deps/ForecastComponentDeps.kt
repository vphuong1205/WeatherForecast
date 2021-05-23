package com.nab.phuong.feature_forecast.deps

import com.nab.phuong.feature_forecast.presentation.ui.ForecastFragment

interface ForecastComponentDeps {
    fun inject(forecastFragment: ForecastFragment)
}
