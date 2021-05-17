package com.nab.phuong.feature_forecast.data.network.response

import com.nab.phuong.feature_forecast.data.network.model.CityNetworkModel
import com.nab.phuong.feature_forecast.data.network.model.ForecastNetworkModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastApiResponse(
    @field:Json(name = CITY_JSON_FIELD) val city: CityNetworkModel,
    @field:Json(name = RESPONSE_CODE_JSON_FIELD) val responseCode: String,
    @field:Json(name = MESSAGE_JSON_FIELD) val message: String,
    @field:Json(name = LIMIT_COUNT_JSON_FIELD) val limitDaysCount: Int,
    @field:Json(name = FORECAST_LIST_JSON_FIELD) val forecastList: List<ForecastNetworkModel>
) {
    companion object {
        const val CITY_JSON_FIELD = "city"
        const val RESPONSE_CODE_JSON_FIELD = "cod"
        const val MESSAGE_JSON_FIELD = "message"
        const val LIMIT_COUNT_JSON_FIELD = "cnt"
        const val FORECAST_LIST_JSON_FIELD = "list"
    }
}
