package com.nab.phuong.feature_forecast.data.mapper

import android.text.TextUtils
import com.nab.phuong.feature_forecast.data.database.model.CityDataModel
import com.nab.phuong.feature_forecast.data.database.model.ForecastDataModel
import com.nab.phuong.feature_forecast.data.network.model.CityNetworkModel
import com.nab.phuong.feature_forecast.data.network.model.ForecastCityClusterModel
import com.nab.phuong.feature_forecast.data.network.model.WeatherNetworkModel
import com.nab.phuong.feature_forecast.domain.model.City
import com.nab.phuong.feature_forecast.domain.model.Forecast
import com.nab.phuong.feature_forecast.data.network.response.ForecastApiResponse
import java.text.SimpleDateFormat
import java.util.Locale

class ForecastMapperImpl : ForecastMapper {

    override fun mapToDatabaseModel(input: CityNetworkModel) = with(input) {
        CityDataModel(cityId = cityId, name = name)
    }

    override fun mapToDatabaseModel(input: ForecastApiResponse) = with(input) {
        forecastList.map {
            mapToDatabaseModel(
                input = ForecastCityClusterModel(
                    cityJson = city,
                    forecastNetworkModel = it
                )
            )
        }
    }

    override fun mapToDomainModel(input: ForecastDataModel) = with(input) {
        Forecast(
            date = date.toDisplayDateTime(),
            averageTemperature = toAverageCelsiusTemperature(
                minTemp = minTemperature,
                maxTemp = maxTemperature
            ),
            pressure = pressure.toString(),
            humidity = humidity.toString(),
            description = description
        )
    }

    override fun mapToDomainModel(input: CityDataModel) = with(input) {
        City(cityId = cityId, name = name)
    }

    override fun mapToDatabaseModel(input: ForecastCityClusterModel) = with(input) {
        ForecastDataModel(
            cityId = cityJson.cityId,
            date = forecastNetworkModel.dateTime.fromSecondToMillisecond(),
            minTemperature = forecastNetworkModel.temp.minimum,
            maxTemperature = forecastNetworkModel.temp.maximum,
            pressure = forecastNetworkModel.pressure,
            humidity = forecastNetworkModel.humidity,
            description = forecastNetworkModel.weathers.toJoinedDescription()
        )
    }

    private fun List<WeatherNetworkModel>.toJoinedDescription() =
        TextUtils.join(DELIMITER, this.map { it.description })

    private fun toAverageCelsiusTemperature(
        minTemp: Double,
        maxTemp: Double
    ) = "${((minTemp + maxTemp) / 2).convertFahrenheitToCelsius()}$CELSIUS_DEGREE_SIGN"

    private fun Long.toDisplayDateTime() =
        SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.getDefault()).format(this)

    private fun Double.convertFahrenheitToCelsius() =
        (this - CONST_F_MINUS_BY) * CONST_F_MULTIPLE_BY

    private fun Long.fromSecondToMillisecond() = this * IN_MILLISECOND

    companion object {
        private const val CELSIUS_DEGREE_SIGN = "°C"
        private const val DATE_FORMAT_PATTERN = "EEE, dd MMM yyyy"
        private const val CONST_F_MINUS_BY = 32
        private const val CONST_F_MULTIPLE_BY = 1.8
        private const val IN_MILLISECOND = 1000
        private const val DELIMITER = ", "
    }
}
