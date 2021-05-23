package com.nab.phuong.weather.deps

import android.app.Application
import com.nab.phuong.core_network.client.NetworkClient
import com.nab.phuong.feature_forecast.deps.ForecastComponentDeps

open class DaggerDepsHolderImpl : DaggerDepsHolder, Application() {

    private lateinit var daggerComponents: DaggerComponents

    fun createDaggerComponents() {
        daggerComponents = DaggerComponents(application = this)
    }

    override fun providesAppComponentDeps(): AppComponentDeps {
        return daggerComponents.appDaggerComponent
    }

    override fun networkClient(): NetworkClient {
        return daggerComponents.networkDaggerComponent.networkClient()
    }

    override fun providesForecastComponentDeps(): ForecastComponentDeps {
        return daggerComponents.forecastDaggerComponent
    }
}
