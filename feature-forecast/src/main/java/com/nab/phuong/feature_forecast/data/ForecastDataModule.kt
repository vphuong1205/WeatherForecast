package com.nab.phuong.feature_forecast.data

import android.content.Context
import com.nab.phuong.core_network.client.NetworkClient
import com.nab.phuong.feature_forecast.BuildConfig
import com.nab.phuong.feature_forecast.data.database.ForecastDatabase
import com.nab.phuong.feature_forecast.data.mapper.ForecastMapper
import com.nab.phuong.feature_forecast.data.mapper.ForecastMapperImpl
import com.nab.phuong.feature_forecast.data.network.WeatherForecastNetworkService
import com.nab.phuong.feature_forecast.data.repository.ForecastRepositoryImpl
import com.nab.phuong.feature_forecast.domain.repository.ForecastRepository
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

    @Provides
    @Singleton
    fun providesForecastDatabase(applicationContext: Context): ForecastDatabase {
        return ForecastDatabase.build(context = applicationContext)
    }

    @Provides
    @Singleton
    fun providesForecastMapperImpl(context: Context): ForecastMapper {
        return ForecastMapperImpl(resource = context.resources)
    }

    @Provides
    @Singleton
    fun providesForecastRepository(
        database: ForecastDatabase,
        apiService: WeatherForecastNetworkService,
        forecastMapper: ForecastMapper
    ): ForecastRepository {
        return ForecastRepositoryImpl(
            forecastDao = database.forecastDao(),
            cityDao = database.cityDao(),
            apiService = apiService,
            forecastMapper = forecastMapper,
            appID = BuildConfig.GRADLE_API_APP_ID
        )
    }
}
