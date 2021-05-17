package com.nab.phuong.feature_forecast.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityNetworkModel(
    @field:Json(name = FIELD_ID) val cityId: Long,
    @field:Json(name = FIELD_NAME) val name: String,
) {
    companion object {
        private const val FIELD_ID = "id"
        private const val FIELD_NAME = "name"
    }
}
