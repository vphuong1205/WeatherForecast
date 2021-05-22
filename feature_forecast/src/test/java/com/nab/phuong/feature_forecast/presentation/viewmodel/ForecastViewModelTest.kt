package com.nab.phuong.feature_forecast.presentation.viewmodel

import androidx.lifecycle.Observer
import com.nab.phuong.feature_forecast.domain.model.Forecast
import com.nab.phuong.feature_forecast.domain.model.ForecastResult
import com.nab.phuong.feature_forecast.domain.usecase.ForecastUseCase
import com.nab.phuong.feature_forecast.presentation.model.CityState
import com.nab.phuong.feature_forecast.presentation.model.ForeCastState
import com.nab.phuong.feature_forecast.utils.DataForTesting
import com.nab.phuong.feature_forecast.utils.InstantExecutorExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
internal class ForecastViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: ForecastViewModel

    @Mock
    private lateinit var forecastUseCase: ForecastUseCase

    @Mock
    private lateinit var testForecastObserver: Observer<ForeCastState>

    @Mock
    private lateinit var cityObserver: Observer<CityState>

    private lateinit var foreCastCaptor: ArgumentCaptor<ForeCastState>
    private lateinit var cityCaptor: ArgumentCaptor<CityState>

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)
        viewModel = ForecastViewModel(
            forecastUseCase = forecastUseCase,
            dispatcher = CoroutinesDispatcherProvider(
                main = testDispatcher,
                computation = testDispatcher,
                io = testDispatcher
            )
        )
        viewModel.forecastState.observeForever(testForecastObserver)
        viewModel.cityState.observeForever(cityObserver)
        foreCastCaptor = argumentCaptor()
        cityCaptor = argumentCaptor()
    }

    @AfterEach
    internal fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    internal fun `Given a cached city list  when load city suggestions then expose city list to observer`() {
        testDispatcher.runBlockingTest {
            val result = ForecastResult.Success(data = DataForTesting.cityList)
            `when`(forecastUseCase.loadCities()).thenReturn(result)

            viewModel.loadCitySuggestions()

            verify(cityObserver, Mockito.times(1)).onChanged(
                cityCaptor.capture()
            )
            val state = cityCaptor.value as CityState.ListData
            assertEquals(state.data, DataForTesting.cityList)
        }
    }

    @Test
    internal fun `Given a city name when search forecast by city success then expose list forecast to observer`() {
        testDispatcher.runBlockingTest {
            val result = ForecastResult.Success<Forecast>(data = listOf())
            `when`(forecastUseCase.searchForecasts(cityName = DataForTesting.LONDON_CITY_NAME)).thenReturn(
                result
            )

            viewModel.searchForecastByCity(cityName = DataForTesting.LONDON_CITY_NAME)

            verify(testForecastObserver, Mockito.times(2)).onChanged(
                foreCastCaptor.capture()
            )

            assertTrue(foreCastCaptor.allValues.size == 2)
            assertTrue(foreCastCaptor.allValues[0] is ForeCastState.Loading)
            assertTrue(foreCastCaptor.allValues[1] is ForeCastState.ListData)
            val lastState = foreCastCaptor.value as ForeCastState.ListData
            assertEquals(lastState.data, result.data)
        }
    }

    @Test
    internal fun `Given a city name when search forecast by city failed then expose error to observer`() {
        testDispatcher.runBlockingTest {
            val result = ForecastResult.Error<Forecast>(
                message = DataForTesting.NETWORK_ERROR_MESSAGE
            )
            `when`(forecastUseCase.searchForecasts(cityName = DataForTesting.LONDON_CITY_NAME)).thenReturn(
                result
            )

            viewModel.searchForecastByCity(cityName = DataForTesting.LONDON_CITY_NAME)

            verify(testForecastObserver, Mockito.times(2)).onChanged(
                foreCastCaptor.capture()
            )
            assertTrue(foreCastCaptor.allValues.size == 2)
            assertTrue(foreCastCaptor.allValues[0] is ForeCastState.Loading)
            assertTrue(foreCastCaptor.allValues[1] is ForeCastState.Error)
            val lastState = foreCastCaptor.value as ForeCastState.Error
            assertEquals(lastState.errorMessage, DataForTesting.NETWORK_ERROR_MESSAGE)
        }
    }

    @Test
    internal fun `Given a city name which cached in database when search forecast by city then expose list forecasts to observer`() {
        testDispatcher.runBlockingTest {
            val cities = ForecastResult.Success(data = listOf(DataForTesting.cityList.first()))
            `when`(
                forecastUseCase.getCityByName(
                    cityName = DataForTesting.LONDON_CITY_NAME
                )
            ).thenReturn(
                cities
            )
            val forecast = ForecastResult.Success<Forecast>(data = listOf())
            `when`(
                forecastUseCase.searchForecasts(
                    cityName = DataForTesting.LONDON_CITY_NAME,
                    cityId = 1
                )
            ).thenReturn(
                forecast
            )

            viewModel.searchForecastByCity(cityName = DataForTesting.LONDON_CITY_NAME)

            verify(testForecastObserver, Mockito.times(2)).onChanged(
                foreCastCaptor.capture()
            )
            assertTrue(foreCastCaptor.allValues.size == 2)
            assertTrue(foreCastCaptor.allValues[0] is ForeCastState.Loading)
            assertTrue(foreCastCaptor.allValues[1] is ForeCastState.ListData)
        }
    }

    private inline fun <reified T : Any> argumentCaptor(): ArgumentCaptor<T> =
        ArgumentCaptor.forClass(T::class.java)
}