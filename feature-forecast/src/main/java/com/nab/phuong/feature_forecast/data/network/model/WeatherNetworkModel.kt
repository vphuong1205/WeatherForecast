package com.nab.phuong.feature_forecast.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherNetworkModel(
    @Json(name = WEATHER_ID_JSON_FIELD) val weatherId: Long,
    @Json(name = DESCRIPTION_JSON_FIELD) val description: String
) {
    companion object {
        private const val WEATHER_ID_JSON_FIELD = "id"
        private const val DESCRIPTION_JSON_FIELD = "description"
    }
}
