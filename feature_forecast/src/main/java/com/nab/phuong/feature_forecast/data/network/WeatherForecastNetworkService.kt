package com.nab.phuong.feature_forecast.data.network

import com.nab.phuong.feature_forecast.data.network.response.ForecastApiResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface WeatherForecastNetworkService {
    @POST(value = API_END_POINT)
    suspend fun searchDailyForecastAsync(
        @Query(value = API_SEARCH_VALUE) cityName: String,
        @Query(value = API_DAYS_COUNT) dayCount: Int,
        @Query(value = API_UNITS) unit: String = DEFAULT_API_UNITS,
        @Query(value = API_APP_ID) appId: String,
    ): ForecastApiResponse

    companion object {
        private const val API_END_POINT = "forecast/daily?"
        private const val API_SEARCH_VALUE = "q"
        private const val API_DAYS_COUNT = "cnt"
        private const val API_UNITS = "units"
        private const val API_APP_ID = "appid"
        private const val DEFAULT_API_UNITS = "metric"
    }
}
