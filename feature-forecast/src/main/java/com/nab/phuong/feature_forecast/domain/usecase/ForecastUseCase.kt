package com.nab.phuong.feature_forecast.domain.usecase

import com.nab.phuong.feature_forecast.domain.model.City
import com.nab.phuong.feature_forecast.domain.model.Forecast
import com.nab.phuong.feature_forecast.domain.model.Result

interface ForecastUseCase {

    suspend fun getCityByName(cityName: String): Result<City>

    suspend fun loadCities(): Result<City>

    suspend fun searchForecasts(cityName: String, cityId: Long? = null): Result<Forecast>

    suspend fun clearExpiredForecasts()
}
