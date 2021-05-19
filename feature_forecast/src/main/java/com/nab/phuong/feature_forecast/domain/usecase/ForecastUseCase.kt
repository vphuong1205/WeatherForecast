package com.nab.phuong.feature_forecast.domain.usecase

import com.nab.phuong.feature_forecast.domain.model.City
import com.nab.phuong.feature_forecast.domain.model.Forecast
import com.nab.phuong.feature_forecast.domain.model.ForecastResult

interface ForecastUseCase {
    suspend fun searchCitySuggestion(cityName: String): ForecastResult<City>

    suspend fun searchForecasts(cityName: String, cityId: String? = null): ForecastResult<Forecast>
}
