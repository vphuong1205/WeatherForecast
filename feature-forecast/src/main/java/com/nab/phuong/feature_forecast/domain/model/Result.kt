package com.nab.phuong.feature_forecast.domain.model

sealed class Result<T> {
    data class Success<T>(val data: List<T>) : Result<T>()
    data class Error<T>(val message: String) : Result<T>()
}
