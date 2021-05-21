package com.nab.phuong.feature_forecast.data.mapper

import android.content.res.Resources
import android.text.TextUtils
import com.nab.phuong.feature_forecast.R
import com.nab.phuong.feature_forecast.data.database.model.CityDataModel
import com.nab.phuong.feature_forecast.data.database.model.ForecastDataModel
import com.nab.phuong.feature_forecast.data.network.model.CityNetworkModel
import com.nab.phuong.feature_forecast.data.network.model.ForecastCityClusterModel
import com.nab.phuong.feature_forecast.data.network.model.WeatherNetworkModel
import com.nab.phuong.feature_forecast.domain.model.City
import com.nab.phuong.feature_forecast.domain.model.Forecast
import com.nab.phuong.feature_forecast.data.network.response.ForecastApiResponse
import retrofit2.HttpException
import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class ForecastMapperImpl @Inject constructor(private val resource: Resources) : ForecastMapper {

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
            date = resource.getString(R.string.feature_forecast_date, date.toDisplayDateTime()),
            averageTemperature = resource.getString(
                R.string.feature_forecast_temperature,
                ((minTemperature + maxTemperature) / 2).toInt()
            ),
            pressure = resource.getString(R.string.feature_forecast_pressure, pressure),
            humidity = resource.getString(R.string.feature_forecast_humidity, humidity),
            description = resource.getString(R.string.feature_forecast_description, description)
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

    override fun parseExceptionToErrorMessage(exception: Exception): String {
        return when (exception) {
            is HttpException -> {
                if (exception.code() == NOT_FOUND_ERROR_CODE) {
                    resource.getString(R.string.feature_forecast_not_found_error_message)
                } else {
                    resource.getString(R.string.feature_forecast_server_error_message)
                }
            }
            is UnknownHostException -> {
                resource.getString(R.string.feature_forecast_server_error_message)
            }
            else -> {
                resource.getString(R.string.feature_forecast_common_error_message)
            }
        }
    }

    private fun List<WeatherNetworkModel>.toJoinedDescription() =
        TextUtils.join(DELIMITER, this.map { it.description })

    private fun Long.toDisplayDateTime() =
        SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.getDefault()).format(this)

    private fun Long.fromSecondToMillisecond() = this * IN_MILLISECOND

    companion object {
        private const val DATE_FORMAT_PATTERN = "EEE, dd MMM yyyy"
        private const val IN_MILLISECOND = 1000
        private const val DELIMITER = ", "
        private const val NOT_FOUND_ERROR_CODE = 404
    }
}
