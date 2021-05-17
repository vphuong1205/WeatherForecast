package com.nab.phuong.feature_forecast.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TemperatureNetworkModel(
    @Json(name = MINIMUM_TEMPERATURE) val minimum: Double,
    @Json(name = MAXIMUM_TEMPERATURE) val maximum: Double
) {
    companion object {
        private const val MINIMUM_TEMPERATURE = "min"
        private const val MAXIMUM_TEMPERATURE = "max"
    }
}
