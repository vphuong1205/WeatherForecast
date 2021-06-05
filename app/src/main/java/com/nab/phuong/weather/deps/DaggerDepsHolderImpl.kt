package com.nab.phuong.weather.deps

import android.app.Application
import com.nab.phuong.core_network.NetworkConfig
import com.nab.phuong.core_network.client.NetworkClient
import com.nab.phuong.feature_forecast.deps.ForecastComponentDeps
import com.nab.phuong.weather.home.deps.HomeComponentDeps

open class DaggerDepsHolderImpl : DaggerDepsHolder, Application() {

    private lateinit var daggerComponents: DaggerComponents

    fun createDaggerComponents() {
        daggerComponents = DaggerComponents(application = this)
    }

    override fun providesAppComponentDeps(): HomeComponentDeps {
        return daggerComponents.homeDaggerComponent
    }

    override fun networkClient(): NetworkClient {
        return daggerComponents.networkDaggerComponent.networkClient()
    }

    override fun networkConfig(): NetworkConfig {
        return daggerComponents.networkDaggerComponent.networkConfig()
    }

    override fun providesForecastComponentDeps(): ForecastComponentDeps {
        return daggerComponents.forecastDaggerComponent
    }
}
