package com.nab.phuong.weather.deps

import android.app.Application
import com.nab.phuong.core_network.NetworkConfig
import com.nab.phuong.core_network.deps.DaggerNetworkComponent
import com.nab.phuong.core_network.deps.NetworkComponent
import com.nab.phuong.weather.presentation.deps.DaggerPresentationComponent
import com.nab.phuong.weather.presentation.deps.PresentationComponent

class DaggerComponents(private val application: Application) {
    private val networkConfig by lazy {
        NetworkConfig(
            connectionTimeout = 30L,
            readTimeout = 30L,
            writeTimeout = 30L,
            baseUrl = "https://api.openweathermap.org/data/2.5/"
        )
    }

    val networkDaggerComponent by lazy {
        getInternalNetworkDaggerComponent()
    }
    val presentationDaggerComponent by lazy {
        getInternalPresentationDaggerComponent()
    }

    private fun getInternalNetworkDaggerComponent(): NetworkComponent {
        return DaggerNetworkComponent
            .builder()
            .networkConfig(networkConfig)
            .application(application)
            .build()
    }

    private fun getInternalPresentationDaggerComponent(): PresentationComponent {
        return DaggerPresentationComponent
            .builder()
            .context(application)
            .networkClient(networkDaggerComponent.networkClient())
            .build()
    }
}
