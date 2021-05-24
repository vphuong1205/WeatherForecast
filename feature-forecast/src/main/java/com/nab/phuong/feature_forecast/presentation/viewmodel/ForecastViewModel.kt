package com.nab.phuong.feature_forecast.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nab.phuong.feature_forecast.domain.model.City
import com.nab.phuong.feature_forecast.domain.model.Forecast
import com.nab.phuong.feature_forecast.domain.model.Result
import com.nab.phuong.feature_forecast.domain.usecase.ForecastUseCase
import com.nab.phuong.feature_forecast.presentation.model.CityState
import com.nab.phuong.feature_forecast.presentation.model.ForeCastState
import com.nab.phuong.lib_utils.coroutines.CoroutinesDispatcherProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForecastViewModel @Inject constructor(
    private val forecastUseCase: ForecastUseCase,
    private val dispatcher: CoroutinesDispatcherProvider,
) : ViewModel() {

    private val _forecastState = MutableLiveData<ForeCastState>()
    val forecastState: LiveData<ForeCastState> = _forecastState

    private val _cityState = MutableLiveData<CityState>()
    val cityState: LiveData<CityState> = _cityState

    init {
        viewModelScope.launch(dispatcher.io) {
            forecastUseCase.clearExpiredForecasts()
        }
    }

    fun searchForecastByCity(cityName: String) {
        viewModelScope.launch(dispatcher.io) {

            _forecastState.postValue(ForeCastState.Loading)

            val cityId = getCityId(result = forecastUseCase.getCityByName(cityName = cityName))

            when (val result =
                forecastUseCase.searchForecasts(cityName = cityName, cityId = cityId)) {
                is Result.Success<Forecast> -> {
                    _forecastState.postValue(ForeCastState.ListData(data = result.data))
                    if (cityId == null) {
                        loadCitySuggestions()
                    }
                }
                is Result.Error -> {
                    _forecastState.postValue(ForeCastState.Error(errorMessage = result.message))
                }
                else -> {
                    _forecastState.postValue(ForeCastState.Empty)
                }
            }
        }
    }

    fun loadCitySuggestions() {
        viewModelScope.launch(dispatcher.io) {
            when (val result = forecastUseCase.loadCities()) {
                is Result.Success<City> -> {
                    _cityState.postValue(CityState.ListData(result.data))
                }
            }
        }
    }

    private fun getCityId(result: Result<City>): Long? =
        if (result is Result.Success && result.data.isNotEmpty()) {
            result.data.first().cityId
        } else {
            null
        }
}
