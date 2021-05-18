package com.nab.phuong.feature_forecast.data.repository

import com.nab.phuong.feature_forecast.data.database.CityDao
import com.nab.phuong.feature_forecast.data.database.ForecastDao
import com.nab.phuong.feature_forecast.data.mapper.ForecastMapper
import com.nab.phuong.feature_forecast.data.network.WeatherForecastNetworkService
import com.nab.phuong.feature_forecast.domain.model.City
import com.nab.phuong.feature_forecast.domain.model.Forecast
import com.nab.phuong.feature_forecast.domain.model.ForecastResult
import com.nab.phuong.feature_forecast.domain.repository.ForecastRepository
import java.net.UnknownHostException

class ForecastRepositoryImpl(
    private val forecastDao: ForecastDao,
    private val cityDao: CityDao,
    private val apiService: WeatherForecastNetworkService,
    private val forecastMapper: ForecastMapper,
    private val appID: String
) : ForecastRepository {

    /**
     * Local first, network only when needed
     */
    override suspend fun queryForecasts(
        cityName: String,
        cityId: String?,
        limitDaysCount: Int
    ): ForecastResult<Forecast> {

        if (!cityId.isNullOrEmpty()) {
            val forecasts = loadForecastsFromLocal(cityId = cityId, limitDaysCount = limitDaysCount)
            if (forecasts.size == limitDaysCount) {
                return ForecastResult.Success(data = forecasts)
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
            ForecastResult.Success(
                data = forecastEntities.map {
                    forecastMapper.mapToDomainModel(
                        input = it
                    )
                }
            )
        } catch (exception: UnknownHostException) {
            ForecastResult.Error(throwable = exception)
        }
    }

    override suspend fun queryCities(cityName: String): ForecastResult<City> {
        val cities = cityDao.getCityByName(city = cityName).map {
            forecastMapper.mapToDomainModel(input = it)
        }
        return ForecastResult.Success(data = cities)
    }

    private suspend fun loadForecastsFromLocal(
        cityId: String,
        limitDaysCount: Int
    ): List<Forecast> {
        return forecastDao.getForecastByCity(
            cityId = cityId,
            limitDays = limitDaysCount,
            dateTime = backToLimitTimesBaseTodayTime(limitDay = limitDaysCount)
        ).map {
            forecastMapper.mapToDomainModel(input = it)
        }
    }

    /**
     * With limitDay =7
     * Get the base time from 7 days before today
     */
    private fun backToLimitTimesBaseTodayTime(limitDay: Int) =
        (System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS) - limitDay * DAY_IN_MILLIS

    companion object {
        const val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
    }
}
