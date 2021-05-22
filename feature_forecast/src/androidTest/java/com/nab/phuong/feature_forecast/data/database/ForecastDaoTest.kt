package com.nab.phuong.feature_forecast.data.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nab.phuong.feature_forecast.data.database.model.ForecastDataModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
open class ForecastDaoTest : ForecastDatabaseTest() {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Throws(Exception::class)
    @Test
    fun givenEmptyDatabase_whenGetForecastByCity_thenReturnEmptyForecastTest() {
        runBlockingTest {

            val forecast = appDatabase.forecastDao().getForecastByCity(
                cityId = TEST_CITY_ID,
                limitDays = 7,
                dateTime = 0L
            )

            assertEquals(expected = forecast.size, actual = 0)
        }
    }

    @Throws(Exception::class)
    @Test
    fun givenValidAndExpiredForecasts_whenSearchForecast_thenReturnOnlyValidForecastTest() {
        runBlockingTest {
            appDatabase.forecastDao().insertForecasts(forecasts = listOf(forecast, expiredForecast))

            val forecast = appDatabase.forecastDao().getForecastByCity(
                cityId = TEST_CITY_ID,
                limitDays = 7,
                dateTime = 700L
            )

            assertEquals(expected = 1, actual = forecast.size)
        }
    }

    @Throws(Exception::class)
    @Test
    fun givenDuplicatedCities_whenGetCityByCity_thenReturnUniqueForecastTest() {
        runBlockingTest {
            appDatabase.forecastDao().insertForecasts(forecasts = listOf(forecast, forecast))

            val forecast = appDatabase.forecastDao().getForecastByCity(
                cityId = TEST_CITY_ID,
                limitDays = 7,
                dateTime = 700L
            )

            assertEquals(forecast.size, 1)
        }
    }

    @Test
    @Throws(Exception::class)
    fun givenValidAndExpiredForecasts_whenDeleteAllForecasts_thenGetCityByCity_thenReturnEmptyTest() {
        runBlockingTest {
            appDatabase.forecastDao().insertForecasts(
                forecasts = listOf(forecast, expiredForecast)
            )

            appDatabase.forecastDao().deleteForecasts()

            val forecasts = appDatabase.forecastDao().getForecastByCity(
                cityId = TEST_CITY_ID,
                limitDays = 7,
                dateTime = 700L
            )

            assertTrue(forecasts.isEmpty())
        }
    }

    companion object {
        private const val TEST_CITY_ID = 100L
        private const val TEST_CITY_ID_2 = 102L

        private val forecast = ForecastDataModel(
            cityId = TEST_CITY_ID,
            date = 1000L,
            minTemperature = 1.0,
            maxTemperature = 1.0,
            pressure = 1,
            humidity = 1000,
            description = "Rain"
        )

        private val expiredForecast = ForecastDataModel(
            cityId = TEST_CITY_ID_2,
            date = 500L,
            minTemperature = 1.0,
            maxTemperature = 1.0,
            pressure = 1,
            humidity = 1000,
            description = "Windy"
        )
    }
}
