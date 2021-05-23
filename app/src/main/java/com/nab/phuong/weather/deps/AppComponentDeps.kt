package com.nab.phuong.weather.deps

import com.nab.phuong.weather.presentation.NavHostActivity

interface AppComponentDeps {
    fun inject(navHostActivity: NavHostActivity)
}
