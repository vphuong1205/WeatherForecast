package com.nab.phuong.feature_forecast.domain.repository

import com.nab.phuong.feature_forecast.domain.model.City
import com.nab.phuong.feature_forecast.domain.model.Forecast
import com.nab.phuong.feature_forecast.domain.model.ForecastResult

interface ForecastRepository {

    suspend fun queryForecasts(
        cityName: String,
        cityId: String? = null,
        limitDaysCount: Int = 7
    ): ForecastResult<Forecast>

    suspend fun queryCities(cityName: String): ForecastResult<City>
}
