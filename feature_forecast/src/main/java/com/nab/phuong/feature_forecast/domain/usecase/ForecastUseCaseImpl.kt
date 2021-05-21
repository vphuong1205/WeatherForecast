package com.nab.phuong.feature_forecast.domain.usecase

import com.nab.phuong.feature_forecast.domain.model.City
import com.nab.phuong.feature_forecast.domain.model.Forecast
import com.nab.phuong.feature_forecast.domain.model.ForecastResult
import com.nab.phuong.feature_forecast.domain.repository.ForecastRepository
import javax.inject.Inject

class ForecastUseCaseImpl @Inject constructor(
    private val forecastRepository: ForecastRepository
) : ForecastUseCase {

    override suspend fun getCityByName(cityName: String): ForecastResult<City> {
        return forecastRepository.getCityByName(cityName)
    }

    override suspend fun loadCities(): ForecastResult<City> {
        return forecastRepository.loadCities()
    }

    override suspend fun searchForecasts(
        cityName: String,
        cityId: Long?
    ): ForecastResult<Forecast> {
        return forecastRepository.queryForecasts(cityName = cityName, cityId = cityId)
    }
}
