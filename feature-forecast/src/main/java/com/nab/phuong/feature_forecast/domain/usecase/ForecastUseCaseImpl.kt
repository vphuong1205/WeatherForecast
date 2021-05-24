package com.nab.phuong.feature_forecast.domain.usecase

import com.nab.phuong.feature_forecast.domain.model.City
import com.nab.phuong.feature_forecast.domain.model.Forecast
import com.nab.phuong.feature_forecast.domain.model.Result
import com.nab.phuong.feature_forecast.domain.repository.ForecastRepository
import javax.inject.Inject

class ForecastUseCaseImpl @Inject constructor(
    private val forecastRepository: ForecastRepository
) : ForecastUseCase {

    override suspend fun getCityByName(cityName: String): Result<City> {
        return forecastRepository.getCityByName(cityName)
    }

    override suspend fun loadCities(): Result<City> {
        return forecastRepository.loadCities()
    }

    override suspend fun searchForecasts(
        cityName: String,
        cityId: Long?
    ): Result<Forecast> {
        return forecastRepository.queryForecasts(cityName = cityName, cityId = cityId)
    }

    override suspend fun clearExpiredForecasts() {
        forecastRepository.clearExpiredForecasts()
    }
}
