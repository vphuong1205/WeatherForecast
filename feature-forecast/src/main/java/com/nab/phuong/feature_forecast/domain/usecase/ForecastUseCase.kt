package com.nab.phuong.feature_forecast.domain.usecase

import com.nab.phuong.feature_forecast.domain.model.City
import com.nab.phuong.feature_forecast.domain.model.Forecast
import com.nab.phuong.feature_forecast.domain.model.ForecastResult

interface ForecastUseCase {

    suspend fun getCityByName(cityName: String): ForecastResult<City>

    suspend fun loadCities(): ForecastResult<City>

    suspend fun searchForecasts(cityName: String, cityId: Long? = null): ForecastResult<Forecast>

    suspend fun clearExpiredForecasts()
}
