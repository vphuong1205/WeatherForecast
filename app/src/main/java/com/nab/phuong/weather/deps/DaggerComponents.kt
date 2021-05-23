package com.nab.phuong.weather.deps

import android.app.Application
import com.nab.phuong.core_network.NetworkConfig
import com.nab.phuong.core_network.deps.DaggerNetworkComponent
import com.nab.phuong.core_network.deps.NetworkComponent
import com.nab.phuong.feature_forecast.deps.DaggerForecastComponent
import com.nab.phuong.feature_forecast.deps.ForecastComponent
import com.nab.phuong.weather.BuildConfig
import com.nab.phuong.weather.home.deps.HomeComponent
import com.nab.phuong.weather.home.deps.DaggerHomeComponent

class DaggerComponents(private val application: Application) {
    private val networkConfig by lazy {
        NetworkConfig(
            baseUrl = BuildConfig.GRADLE_API_BASE_URL,
            pinnerHost = BuildConfig.GRADLE_PINNING_CERTIFICATE_PATTERN,
            pinnerCertificates = listOf(
                BuildConfig.GRADLE_PINNING_CERTIFICATE_MAIN,
                BuildConfig.GRADLE_PINNING_CERTIFICATE_BACKUP1,
                BuildConfig.GRADLE_PINNING_CERTIFICATE_BACKUP2
            )
        )
    }

    val homeDaggerComponent by lazy {
        getInternalHomeDaggerComponent()
    }

    val networkDaggerComponent by lazy {
        getInternalNetworkDaggerComponent()
    }

    val forecastDaggerComponent by lazy {
        getInternalForecastDaggerComponent()
    }

    private fun getInternalHomeDaggerComponent(): HomeComponent {
        return DaggerHomeComponent
            .builder()
            .context(application)
            .build()
    }

    private fun getInternalNetworkDaggerComponent(): NetworkComponent {
        return DaggerNetworkComponent
            .builder()
            .networkConfig(networkConfig)
            .application(application)
            .build()
    }

    private fun getInternalForecastDaggerComponent(): ForecastComponent {
        return DaggerForecastComponent
            .builder()
            .context(application)
            .networkClient(networkDaggerComponent.networkClient())
            .build()
    }
}
