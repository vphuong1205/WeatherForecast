package com.nab.phuong.feature_forecast.utils

import com.nab.phuong.feature_forecast.data.database.model.ForecastDataModel
import com.nab.phuong.feature_forecast.data.network.model.CityNetworkModel
import com.nab.phuong.feature_forecast.data.network.response.ForecastApiResponse
import com.nab.phuong.feature_forecast.domain.model.City
import com.nab.phuong.feature_forecast.domain.model.Forecast

object DataForTesting {
    internal const val INVALID_CITY_NAME = "Dummy city"
    internal const val LONDON_CITY_NAME = "London"
    internal val londonCity = City(cityId = 1, name = LONDON_CITY_NAME)

    internal const val NETWORK_ERROR_MESSAGE = "Network error happened"
    internal val forecast = Forecast(
        date = "Today",
        averageTemperature = "20",
        pressure = "123",
        humidity = "345",
        description = "Windy"
    )
    internal val forecastDataModel = ForecastDataModel(
        cityId = 1,
        date = 1,
        minTemperature = 1.0,
        maxTemperature = 1.0,
        pressure = 1,
        humidity = 1,
        description = ""
    )
    internal val forecastDataList =
        listOf(
            forecastDataModel,
            forecastDataModel,
            forecastDataModel,
            forecastDataModel,
            forecastDataModel,
            forecastDataModel,
            forecastDataModel
        )

    private val cityNetwork = CityNetworkModel(
        cityId = 1,
        name = "Hanoi",
    )

    internal val response = ForecastApiResponse(
        city = cityNetwork,
        responseCode = "200",
        message = "ok",
        limitDaysCount = 7,
        forecastList = listOf()
    )
}
