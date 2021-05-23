package com.nab.phuong.feature_forecast.domain.model

data class Forecast(
    val date: String,
    val averageTemperature: String,
    val pressure: String,
    val humidity: String,
    val description: String
)
