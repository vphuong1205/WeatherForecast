package com.nab.phuong.feature_forecast.data.network

import com.nab.phuong.feature_forecast.data.network.response.ForecastApiResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface WeatherForecastNetworkService {
    @POST(value = API_END_POINT)
    suspend fun searchDailyForecastAsync(
        @Query(value = API_SEARCH_VALUE) cityName: String,
        @Query(value = API_DAYS_COUNT) dayCount: Int,
        @Query(value = API_APP_ID) appId: String,
    ): ForecastApiResponse

    companion object {
        const val API_END_POINT = "forecast/daily?"
        const val API_SEARCH_VALUE = "q"
        const val API_DAYS_COUNT = "cnt"
        const val API_APP_ID = "appid"
    }
}
