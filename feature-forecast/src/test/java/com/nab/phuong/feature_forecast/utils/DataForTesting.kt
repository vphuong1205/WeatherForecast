package com.nab.phuong.feature_forecast.utils

import com.nab.phuong.feature_forecast.data.database.model.CityDataModel
import com.nab.phuong.feature_forecast.data.database.model.ForecastDataModel
import com.nab.phuong.feature_forecast.data.network.model.CityNetworkModel
import com.nab.phuong.feature_forecast.data.network.model.ForecastNetworkModel
import com.nab.phuong.feature_forecast.data.network.model.TemperatureNetworkModel
import com.nab.phuong.feature_forecast.data.network.model.WeatherNetworkModel
import com.nab.phuong.feature_forecast.data.network.response.ForecastApiResponse
import com.nab.phuong.feature_forecast.domain.model.City
import com.nab.phuong.feature_forecast.domain.model.Forecast
import com.squareup.moshi.Json

object DataForTesting {
    internal const val INVALID_CITY_NAME = "Dummy city"
    internal const val LONDON_CITY_NAME = "London"
    internal val londonCity = City(cityId = 1, name = LONDON_CITY_NAME)

    internal const val NETWORK_ERROR_MESSAGE = "Network error happened"

    internal val cityList = listOf(
        City(cityId = 1, name = LONDON_CITY_NAME),
        City(cityId = 2, name = "Paris"),
        City(cityId = 3, name = "Saigon City")
    )

    internal val forecast = Forecast(
        date = "Today",
        averageTemperature = "20",
        pressure = "123",
        humidity = "345",
        description = "Windy"
    )

    internal val forecastList = listOf(
        forecast,
        forecast,
        forecast,
        forecast,
        forecast,
        forecast
    )

    internal val forecastDataModel = ForecastDataModel(
        cityId = 1,
        date = 1621700330691L,
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

    internal val cityNetwork = CityNetworkModel(
        cityId = 1,
        name = LONDON_CITY_NAME,
    )

    internal val cityDataModel = CityDataModel(
        cityId = 1,
        name = LONDON_CITY_NAME,
    )

    private val temperatureNetwork = TemperatureNetworkModel(
        minimum = 10.0,
        maximum = 20.0
    )
    private val weatherNetworkModel = WeatherNetworkModel(
        weatherId = 123,
        description = "Raining"
    )
    internal val forecastNetworkModel = ForecastNetworkModel(
        dateTime = 1000L,
        temp = temperatureNetwork,
        pressure = 100,
        humidity = 100,
        weathers = listOf(weatherNetworkModel, weatherNetworkModel)
    )
    internal val response = ForecastApiResponse(
        city = cityNetwork,
        responseCode = "200",
        message = "ok",
        limitDaysCount = 7,
        forecastList = listOf(
            forecastNetworkModel,
            forecastNetworkModel,
            forecastNetworkModel,
            forecastNetworkModel,
            forecastNetworkModel,
            forecastNetworkModel,
            forecastNetworkModel
        )
    )
}
