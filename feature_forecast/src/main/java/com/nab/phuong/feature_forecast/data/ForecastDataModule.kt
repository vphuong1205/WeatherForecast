package com.nab.phuong.feature_forecast.data

import com.nab.phuong.core_network.client.NetworkClient
import com.nab.phuong.feature_forecast.data.network.WeatherForecastNetworkService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ForecastDataModule {

    @Provides
    @Singleton
    fun providesForecastNetworkService(networkClient: NetworkClient): WeatherForecastNetworkService {
        return networkClient.create(WeatherForecastNetworkService::class.java)
    }
}
