package com.nab.phuong.weather.deps

import com.nab.phuong.core_network.deps.NetworkDepsProvider
import com.nab.phuong.weather.presentation.deps.PresentationDepsProvider

interface DaggerDepsHolder : NetworkDepsProvider, PresentationDepsProvider
