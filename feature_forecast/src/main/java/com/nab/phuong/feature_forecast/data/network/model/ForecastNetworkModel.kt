package com.nab.phuong.feature_forecast.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastNetworkModel(
    @Json(name = DATE_TIME_JSON_FIELD) val dateTime: Long,
    @Json(name = TEMPERATURE_JSON_FIELD) val temp: TemperatureNetworkModel,
    @Json(name = PRESSURE_JSON_FIELD) val pressure: Int,
    @Json(name = HUMIDITY_JSON_FIELD) val humidity: Int,
    @Json(name = WEATHER_LIST_JSON_FIELD) val weathers: List<WeatherNetworkModel>
) {
    companion object {
        private const val DATE_TIME_JSON_FIELD = "dt"
        private const val TEMPERATURE_JSON_FIELD = "temp"
        private const val PRESSURE_JSON_FIELD = "pressure"
        private const val HUMIDITY_JSON_FIELD = "humidity"
        private const val WEATHER_LIST_JSON_FIELD = "weather"
    }
}
