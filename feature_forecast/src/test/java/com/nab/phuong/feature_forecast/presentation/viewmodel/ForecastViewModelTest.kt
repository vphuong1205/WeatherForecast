package com.nab.phuong.feature_forecast.presentation.viewmodel

import androidx.lifecycle.Observer
import com.nab.phuong.feature_forecast.domain.model.City
import com.nab.phuong.feature_forecast.domain.model.Forecast
import com.nab.phuong.feature_forecast.domain.model.ForecastResult
import com.nab.phuong.feature_forecast.domain.usecase.ForecastUseCase
import com.nab.phuong.feature_forecast.presentation.model.CityState
import com.nab.phuong.feature_forecast.presentation.model.ForeCastState
import com.nab.phuong.feature_forecast.utils.InstantExecutorExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
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
            val result = ForecastResult.Success(data = cityList)
            `when`(forecastUseCase.loadCities()).thenReturn(result)

            viewModel.loadCitySuggestions()

            verify(cityObserver, Mockito.times(1)).onChanged(
                cityCaptor.capture()
            )
            val state = cityCaptor.value as CityState.ListData
            assertEquals(state.data, cityList)
        }
    }

    @Test
    internal fun `Given a city name when search forecast by city success then expose list forecast to observer`() {
        testDispatcher.runBlockingTest {
            val result = ForecastResult.Success<Forecast>(data = listOf())
            `when`(forecastUseCase.searchForecasts(cityName = LONDON_CITY)).thenReturn(result)

            viewModel.searchForecastByCity(cityName = LONDON_CITY)

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
            val result = ForecastResult.Error<Forecast>(message = NETWORK_ERROR_MESSAGE)
            `when`(forecastUseCase.searchForecasts(cityName = LONDON_CITY)).thenReturn(result)

            viewModel.searchForecastByCity(cityName = LONDON_CITY)

            verify(testForecastObserver, Mockito.times(2)).onChanged(
                foreCastCaptor.capture()
            )
            assertTrue(foreCastCaptor.allValues.size == 2)
            assertTrue(foreCastCaptor.allValues[0] is ForeCastState.Loading)
            assertTrue(foreCastCaptor.allValues[1] is ForeCastState.Error)
            val lastState = foreCastCaptor.value as ForeCastState.Error
            assertEquals(lastState.errorMessage, NETWORK_ERROR_MESSAGE)
        }
    }

    @Test
    internal fun `Given a city name which cached in database when search forecast by city then expose list forecasts to observer`() {
        testDispatcher.runBlockingTest {
            val cities = ForecastResult.Success(data = listOf(cityList.first()))
            `when`(forecastUseCase.getCityByName(cityName = LONDON_CITY)).thenReturn(
                cities
            )
            val forecast = ForecastResult.Success<Forecast>(data = listOf())
            `when`(forecastUseCase.searchForecasts(cityName = LONDON_CITY, cityId = 1)).thenReturn(
                forecast
            )

            viewModel.searchForecastByCity(cityName = LONDON_CITY)

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

    companion object {
        private const val NETWORK_ERROR_MESSAGE = "Net work error happened"
        private const val LONDON_CITY = "London"
        private const val PARIS_CITY = "Paris"
        private const val HCM_CITY = "Saigon City"
        private val cityList = listOf(
            City(cityId = 1, name = LONDON_CITY),
            City(cityId = 2, name = PARIS_CITY),
            City(cityId = 3, name = HCM_CITY)
        )
    }
}