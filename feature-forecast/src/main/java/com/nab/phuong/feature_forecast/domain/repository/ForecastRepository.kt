package com.nab.phuong.feature_forecast.domain.repository

import com.nab.phuong.feature_forecast.domain.model.City
import com.nab.phuong.feature_forecast.domain.model.Forecast
import com.nab.phuong.feature_forecast.domain.model.ForecastResult

interface ForecastRepository {

    suspend fun getCityByName(cityName: String): ForecastResult<City>

    suspend fun queryForecasts(
        cityName: String,
        cityId: Long? = null,
        limitDaysCount: Int = 7
    ): ForecastResult<Forecast>

    suspend fun loadCities(): ForecastResult<City>

    suspend fun clearExpiredForecasts()
}
