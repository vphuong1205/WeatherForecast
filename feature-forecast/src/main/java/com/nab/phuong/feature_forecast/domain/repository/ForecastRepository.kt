package com.nab.phuong.feature_forecast.domain.repository

import com.nab.phuong.feature_forecast.domain.model.City
import com.nab.phuong.feature_forecast.domain.model.Forecast
import com.nab.phuong.feature_forecast.domain.model.Result

interface ForecastRepository {

    suspend fun getCityByName(cityName: String): Result<City>

    suspend fun queryForecasts(
        cityName: String,
        cityId: Long? = null,
        limitDaysCount: Int = 7
    ): Result<Forecast>

    suspend fun loadCities(): Result<City>

    suspend fun clearExpiredForecasts()
}
