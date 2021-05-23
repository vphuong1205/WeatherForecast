package com.nab.phuong.weather.deps

import com.nab.phuong.core_network.deps.NetworkDepsProvider
import com.nab.phuong.feature_forecast.deps.ForecastDepsProvider

interface DaggerDepsHolder : AppDepsProvider, NetworkDepsProvider, ForecastDepsProvider
