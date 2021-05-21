package com.nab.phuong.feature_forecast.domain.model

sealed class ForecastResult<T> {
    data class Success<T>(val data: List<T>) : ForecastResult<T>()
    data class Error<T>(val message: String) : ForecastResult<T>()
}
