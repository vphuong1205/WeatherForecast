package com.nab.phuong.weather.deps

import com.nab.phuong.core_network.deps.NetworkDepsProvider
import com.nab.phuong.feature_forecast.deps.ForecastDepsProvider
import com.nab.phuong.weather.home.deps.HomeDepsProvider

interface DaggerDepsHolder : HomeDepsProvider, NetworkDepsProvider, ForecastDepsProvider
