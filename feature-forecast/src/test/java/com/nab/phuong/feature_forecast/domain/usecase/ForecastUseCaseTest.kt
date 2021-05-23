package com.nab.phuong.feature_forecast.domain.usecase

import com.nab.phuong.feature_forecast.domain.model.Forecast
import com.nab.phuong.feature_forecast.domain.model.ForecastResult
import com.nab.phuong.feature_forecast.domain.repository.ForecastRepository
import com.nab.phuong.feature_forecast.utils.DataForTesting
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class ForecastUseCaseTest {

    private lateinit var forecastUseCase: ForecastUseCase

    @Mock
    private lateinit var forecastRepository: ForecastRepository

    @BeforeEach
    internal fun setUp() {
        MockitoAnnotations.initMocks(this)
        forecastUseCase = ForecastUseCaseImpl(forecastRepository = forecastRepository)
    }

    @Test
    fun `Given a city name when get city by name success then return success cities result to caller`() {
        runBlockingTest {
            val expectation = ForecastResult.Success(data = DataForTesting.cityList)
            `when`(forecastRepository.getCityByName(cityName = DataForTesting.LONDON_CITY_NAME)).thenReturn(
                expectation
            )

            val result = forecastUseCase.getCityByName(cityName = DataForTesting.LONDON_CITY_NAME)

            assertEquals(expectation, result)
        }
    }

    @Test
    fun `Given a cities cached in repo when load city suggestions then return success cities result to caller`() {
        runBlockingTest {
            val expectation = ForecastResult.Success(data = DataForTesting.cityList)
            `when`(forecastRepository.loadCities()).thenReturn(expectation)

            val result = forecastUseCase.loadCities()

            assertEquals(expectation, result)
        }
    }

    @Test
    fun `Given a city name when search forecast success then return forecasts success result to caller`() {
        runBlockingTest {
            val expectation = ForecastResult.Success(data = DataForTesting.forecastList)
            `when`(forecastRepository.queryForecasts(cityName = DataForTesting.LONDON_CITY_NAME)).thenReturn(
                expectation
            )

            val result = forecastUseCase.searchForecasts(cityName = DataForTesting.LONDON_CITY_NAME)

            assertEquals(expectation, result)
        }
    }

    @Test
    fun `Given a city name when search forecast failed then return forecasts failed result to caller`() {
        runBlockingTest {
            val expectation = ForecastResult.Error<Forecast>(DataForTesting.NETWORK_ERROR_MESSAGE)
            `when`(forecastRepository.queryForecasts(cityName = DataForTesting.LONDON_CITY_NAME)).thenReturn(
                expectation
            )

            val result = forecastUseCase.searchForecasts(cityName = DataForTesting.LONDON_CITY_NAME)

            assertEquals(expectation, result)
        }
    }

}
