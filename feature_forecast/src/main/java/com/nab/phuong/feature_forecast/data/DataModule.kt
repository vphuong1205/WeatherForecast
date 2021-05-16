package com.nab.phuong.feature_forecast.data.network

import com.nab.phuong.core_network.client.NetworkClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesForecastNetworkService(networkClient: NetworkClient): WeatherForecastNetworkService {
        return networkClient.create(WeatherForecastNetworkService::class.java)
    }
}
