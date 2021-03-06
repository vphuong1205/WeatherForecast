package com.nab.phuong.feature_forecast.data.repository

import androidx.annotation.VisibleForTesting
import com.nab.phuong.feature_forecast.data.database.CityDao
import com.nab.phuong.feature_forecast.data.database.ForecastDao
import com.nab.phuong.feature_forecast.data.mapper.ForecastMapper
import com.nab.phuong.feature_forecast.data.network.WeatherForecastNetworkService
import com.nab.phuong.feature_forecast.domain.model.City
import com.nab.phuong.feature_forecast.domain.model.Forecast
import com.nab.phuong.feature_forecast.domain.model.Result
import com.nab.phuong.feature_forecast.domain.repository.ForecastRepository

class ForecastRepositoryImpl(
    private val forecastDao: ForecastDao,
    private val cityDao: CityDao,
    private val apiService: WeatherForecastNetworkService,
    private val forecastMapper: ForecastMapper,
    private val appID: String
) : ForecastRepository {

    override suspend fun getCityByName(cityName: String): Result<City> {
        cityDao.getCityByName(cityName = cityName)?.let {
            return Result.Success(listOf(forecastMapper.mapToDomainModel(input = it)))
        }
        return Result.Success(listOf())
    }

    /**
     * Local first, network only when needed
     */
    override suspend fun queryForecasts(
        cityName: String,
        cityId: Long?,
        limitDaysCount: Int
    ): Result<Forecast> {

        if (cityId != null) {
            val forecasts = loadForecastsFromLocal(cityId = cityId, limitDaysCount = limitDaysCount)
            if (forecasts.size == limitDaysCount) {
                return Result.Success(data = forecasts)
            }
        }

        return try {
            val forecastApiResponse = apiService.searchDailyForecastAsync(
                cityName = cityName,
                dayCount = limitDaysCount,
                appId = appID
            )
            val forecastEntities = forecastMapper.mapToDatabaseModel(input = forecastApiResponse)
            cityDao.insertCity(city = forecastMapper.mapToDatabaseModel(input = forecastApiResponse.city))
            forecastDao.insertForecasts(forecasts = forecastEntities)
            Result.Success(
                data = forecastEntities.map {
                    forecastMapper.mapToDomainModel(
                        input = it
                    )
                }
            )
        } catch (exception: Exception) {
            Result.Error(forecastMapper.parseExceptionToErrorMessage(exception = exception))
        }
    }

    override suspend fun loadCities(): Result<City> {
        val cities = cityDao.getAllCities().map {
            forecastMapper.mapToDomainModel(input = it)
        }
        return Result.Success(data = cities)
    }

    /**
     * To save cached database we will delete all forecast from yesterday and sooner,
     * they are expired and will not be show anymore
     */
    override suspend fun clearExpiredForecasts() {
        forecastDao.deleteExpiredForecasts(
            expiredDate = getTodayBaseTime()
        )
    }

    private suspend fun loadForecastsFromLocal(
        cityId: Long,
        limitDaysCount: Int
    ): List<Forecast> {
        return forecastDao.getForecastByCity(
            cityId = cityId,
            limitDays = limitDaysCount,
            dateTime = getTodayBaseTime()
        ).map {
            forecastMapper.mapToDomainModel(input = it)
        }
    }

    @VisibleForTesting
    fun getTodayBaseTime() = (System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS)

    companion object {
        const val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
    }
}
