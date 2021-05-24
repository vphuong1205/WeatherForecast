package com.nab.phuong.feature_forecast.data

import com.nab.phuong.feature_forecast.data.database.CityDao
import com.nab.phuong.feature_forecast.data.database.ForecastDao
import com.nab.phuong.feature_forecast.data.database.model.CityDataModel
import com.nab.phuong.feature_forecast.data.mapper.ForecastMapper
import com.nab.phuong.feature_forecast.data.network.WeatherForecastNetworkService
import com.nab.phuong.feature_forecast.data.repository.ForecastRepositoryImpl
import com.nab.phuong.feature_forecast.DataForTesting
import com.nab.phuong.feature_forecast.domain.model.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class ForecastRepositoryImplTest {

    private lateinit var forecastRepository: ForecastRepositoryImpl

    @Mock
    private lateinit var forecastDao: ForecastDao

    @Mock
    private lateinit var cityDao: CityDao

    @Mock
    private lateinit var apiService: WeatherForecastNetworkService

    @Mock
    private lateinit var forecastMapper: ForecastMapper

    @Mock
    private lateinit var httpException: HttpException

    private val appID = "APP_API_ID"

    @BeforeEach
    internal fun setUp() {
        MockitoAnnotations.initMocks(this)
        forecastRepository = ForecastRepositoryImpl(
            forecastDao = forecastDao,
            cityDao = cityDao,
            apiService = apiService,
            forecastMapper = forecastMapper,
            appID = appID
        )
    }

    @Test
    fun `Given a city name when get city by name success then return success cities result to caller`() {
        runBlockingTest {
            val expectation = CityDataModel(cityId = 1, name = DataForTesting.LONDON_CITY_NAME)
            `when`(cityDao.getCityByName(cityName = DataForTesting.LONDON_CITY_NAME)).thenReturn(
                expectation
            )

            `when`(forecastMapper.mapToDomainModel(input = expectation)).thenReturn(
                DataForTesting.londonCity
            )
            val result =
                forecastRepository.getCityByName(cityName = DataForTesting.LONDON_CITY_NAME)

            assertTrue(result is Result.Success)
            assertEquals(DataForTesting.londonCity, result.data[0])
        }
    }

    @Test
    fun `Given a invalid city name when get city by name success then return success empty cities to caller`() {
        runBlockingTest {
            `when`(cityDao.getCityByName(cityName = DataForTesting.INVALID_CITY_NAME)).thenReturn(
                null
            )

            val result =
                forecastRepository.getCityByName(cityName = DataForTesting.INVALID_CITY_NAME)

            assertTrue(result is Result.Success)
            assertTrue(result.data.isEmpty())
        }
    }

    @Test
    fun `Given a cities cached in database when load city suggestions then return success cities result to caller`() {
        runBlockingTest {
            val londonCityData = CityDataModel(cityId = 1, name = DataForTesting.LONDON_CITY_NAME)
            val citiesData = listOf(londonCityData)
            `when`(cityDao.getAllCities()).thenReturn(citiesData)
            `when`(forecastMapper.mapToDomainModel(input = londonCityData)).thenReturn(
                DataForTesting.londonCity
            )

            val result = forecastRepository.loadCities()

            assertTrue(result is Result.Success)
            assertEquals(DataForTesting.londonCity, result.data[0])
        }
    }

    @Test
    fun `Given no city cached in database when load city suggestions then return success empty city result to caller`() {
        runBlockingTest {
            val citiesData = listOf<CityDataModel>()
            `when`(cityDao.getAllCities()).thenReturn(citiesData)

            val result = forecastRepository.loadCities()

            assertTrue(result is Result.Success)
            assertTrue(result.data.isEmpty())
        }
    }

    @Test
    fun `Given valid cached forecasts in database of a city when search forecast for that city success then return cached forecasts success result to caller`() {
        runBlockingTest {
            `when`(
                forecastDao.getForecastByCity(
                    cityId = 1,
                    limitDays = 7,
                    dateTime = forecastRepository.getTodayBaseTime()
                )
            ).thenReturn(
                DataForTesting.forecastDataList
            )

            `when`(
                forecastMapper.mapToDomainModel(
                    input = DataForTesting.forecastDataModel
                )
            ).thenReturn(
                DataForTesting.forecast
            )

            val result =
                forecastRepository.queryForecasts(
                    cityId = 1,
                    cityName = DataForTesting.LONDON_CITY_NAME
                )

            assertTrue(result is Result.Success)
            assertEquals(result.data.size, DataForTesting.forecastDataList.size)
            result.data.forEach {
                assertEquals(it, DataForTesting.forecast)
            }
        }
    }

    @Test
    fun `Given invalid cached forecasts and network error when search forecast for that city success then return error result to caller`() {
        runBlockingTest {
            `when`(
                forecastDao.getForecastByCity(
                    cityId = 1,
                    limitDays = 7,
                    dateTime = forecastRepository.getTodayBaseTime()
                )
            ).thenReturn(
                listOf()
            )

            `when`(
                apiService.searchDailyForecastAsync(
                    cityName = DataForTesting.LONDON_CITY_NAME,
                    dayCount = 7,
                    appId = appID
                )
            ).thenThrow(httpException)

            `when`(forecastMapper.parseExceptionToErrorMessage(httpException)).thenReturn(
                DataForTesting.NETWORK_ERROR_MESSAGE
            )
            val result = forecastRepository.queryForecasts(
                cityId = 1, cityName = DataForTesting.LONDON_CITY_NAME
            )

            assertTrue(result is Result.Error)
            assertEquals(result.message, DataForTesting.NETWORK_ERROR_MESSAGE)
        }
    }

    @Test
    fun `Given invalid cached forecasts of city when search forecast network success then return success result to caller`() {
        runBlockingTest {
            `when`(
                forecastDao.getForecastByCity(
                    cityId = 1,
                    limitDays = 7,
                    dateTime = forecastRepository.getTodayBaseTime()
                )
            ).thenReturn(
                listOf()
            )

            `when`(
                apiService.searchDailyForecastAsync(
                    cityName = DataForTesting.LONDON_CITY_NAME,
                    dayCount = 7,
                    appId = appID
                )
            ).thenReturn(DataForTesting.response)

            `when`(forecastMapper.mapToDatabaseModel(DataForTesting.response)).thenReturn(
                DataForTesting.forecastDataList
            )

            `when`(
                forecastMapper.mapToDomainModel(
                    input = DataForTesting.forecastDataModel
                )
            ).thenReturn(
                DataForTesting.forecast
            )

            val result =
                forecastRepository.queryForecasts(
                    cityId = 1, cityName = DataForTesting.LONDON_CITY_NAME
                )

            assertTrue(result is Result.Success)
            assertEquals(result.data.size, 7)
            result.data.forEach {
                assertEquals(it, DataForTesting.forecast)
            }
        }
    }

}