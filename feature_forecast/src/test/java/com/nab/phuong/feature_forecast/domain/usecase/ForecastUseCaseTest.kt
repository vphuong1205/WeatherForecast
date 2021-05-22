package com.nab.phuong.feature_forecast.domain.usecase

import com.nab.phuong.feature_forecast.domain.model.City
import com.nab.phuong.feature_forecast.domain.model.Forecast
import com.nab.phuong.feature_forecast.domain.model.ForecastResult
import com.nab.phuong.feature_forecast.domain.repository.ForecastRepository
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
            val expectation = ForecastResult.Success(data = cityList)
            `when`(forecastRepository.getCityByName(cityName = LONDON_CITY)).thenReturn(expectation)

            val result = forecastUseCase.getCityByName(cityName = LONDON_CITY)

            assertEquals(expectation, result)
        }
    }

    @Test
    fun `Given a cities cached in repo when load city suggestions then return success cities result to caller`() {
        runBlockingTest {
            val expectation = ForecastResult.Success(data = cityList)
            `when`(forecastRepository.loadCities()).thenReturn(expectation)

            val result = forecastUseCase.loadCities()

            assertEquals(expectation, result)
        }
    }

    @Test
    fun `Given a city name when search forecast success then return forecasts success result to caller`() {
        runBlockingTest {
            val expectation = ForecastResult.Success(data = forecastList)
            `when`(forecastRepository.queryForecasts(cityName = LONDON_CITY)).thenReturn(expectation)

            val result = forecastUseCase.searchForecasts(cityName = LONDON_CITY)

            assertEquals(expectation, result)
        }
    }

    @Test
    fun `Given a city name when search forecast failed then return forecasts failed result to caller`() {
        runBlockingTest {
            val expectation = ForecastResult.Error<Forecast>(NETWORK_ERROR_MESSAGE)
            `when`(forecastRepository.queryForecasts(cityName = LONDON_CITY)).thenReturn(expectation)

            val result = forecastUseCase.searchForecasts(cityName = LONDON_CITY)

            assertEquals(expectation, result)
        }
    }

    companion object {
        private const val NETWORK_ERROR_MESSAGE = "Network error happened"

        private const val LONDON_CITY = "London"
        private const val PARIS_CITY = "Paris"
        private const val HCM_CITY = "Saigon City"
        private val cityList = listOf(
            City(cityId = 1, name = LONDON_CITY),
            City(cityId = 2, name = PARIS_CITY),
            City(cityId = 3, name = HCM_CITY)
        )

        private val forecastList = listOf(
            Forecast(
                date = "Today",
                averageTemperature = "20",
                pressure = "123",
                humidity = "345",
                description = "Windy"
            ),
            Forecast(
                date = "Today",
                averageTemperature = "20",
                pressure = "123",
                humidity = "345",
                description = "Windy"
            ),
            Forecast(
                date = "Today",
                averageTemperature = "20",
                pressure = "123",
                humidity = "345",
                description = "Windy"
            ),
            Forecast(
                date = "Today",
                averageTemperature = "20",
                pressure = "123",
                humidity = "345",
                description = "Windy"
            ),
            Forecast(
                date = "Today",
                averageTemperature = "20",
                pressure = "123",
                humidity = "345",
                description = "Windy"
            ),
            Forecast(
                date = "Today",
                averageTemperature = "20",
                pressure = "123",
                humidity = "345",
                description = "Windy"
            )
        )
    }
}
