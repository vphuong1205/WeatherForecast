package com.nab.phuong.feature_forecast.data.mapper

import android.content.res.Resources
import com.nab.phuong.feature_forecast.R
import com.nab.phuong.feature_forecast.utils.DataForTesting
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.test.assertEquals

class ForecastMapperTest {
    private lateinit var mapper: ForecastMapperImpl

    @Mock
    private lateinit var resources: Resources

    @BeforeEach
    internal fun setUp() {
        MockitoAnnotations.initMocks(this)
        mapper = ForecastMapperImpl(resource = resources)
    }

    @Test
    fun `Given a city from network when map to city database model then return database model`() {
        val dataModel = mapper.mapToDatabaseModel(input = DataForTesting.cityNetwork)

        assertEquals(dataModel.name, DataForTesting.cityNetwork.name)
        assertEquals(dataModel.cityId, DataForTesting.cityNetwork.cityId)
    }

    @Test
    fun `Given an api response when map to database forecast models then return database database forecast models`() {
        val forecastDataModels = mapper.mapToDatabaseModel(input = DataForTesting.response)

        assertEquals(forecastDataModels.size, DataForTesting.response.forecastList.size)
        forecastDataModels.forEach {
            assertEquals(it.cityId, DataForTesting.response.city.cityId)
            assertEquals(it.date, DataForTesting.forecastNetworkModel.dateTime * 1000)
            assertEquals(it.humidity, DataForTesting.forecastNetworkModel.humidity)
            assertEquals(it.pressure, DataForTesting.forecastNetworkModel.pressure)
            assertEquals(it.minTemperature, DataForTesting.forecastNetworkModel.temp.minimum)
            assertEquals(it.maxTemperature, DataForTesting.forecastNetworkModel.temp.maximum)
            assertEquals(it.description, DataForTesting.forecastNetworkModel.weathers.joinToString(
                separator = ", "
            ) { weather -> weather.description })
        }
    }

    @Test
    fun `Given a forecast data model when map to domain model then return forecast domain model`() {
        `when`(
            resources.getString(
                R.string.feature_forecast_date,
                1621700330691L.toDisplayDateTime()
            )
        ).thenReturn(TODAY)

        `when`(
            resources.getString(
                R.string.feature_forecast_temperature,
                ((DataForTesting.forecastDataModel.maxTemperature + DataForTesting.forecastDataModel.maxTemperature) / 2).toInt()
            )
        ).thenReturn(AVG_TEMPERATURE)

        `when`(
            resources.getString(
                R.string.feature_forecast_pressure,
                DataForTesting.forecastDataModel.pressure
            )
        ).thenReturn(PRESSURE)

        `when`(
            resources.getString(
                R.string.feature_forecast_humidity,
                DataForTesting.forecastDataModel.humidity
            )
        ).thenReturn(HUMIDITY)

        `when`(
            resources.getString(
                R.string.feature_forecast_description,
                DataForTesting.forecastDataModel.description
            )
        ).thenReturn(DESCRIPTION)

        val domainModel = mapper.mapToDomainModel(input = DataForTesting.forecastDataModel)

        assertEquals(domainModel.date, TODAY)
        assertEquals(domainModel.averageTemperature, AVG_TEMPERATURE)
        assertEquals(domainModel.pressure, PRESSURE)
        assertEquals(domainModel.humidity, HUMIDITY)
        assertEquals(domainModel.description, DESCRIPTION)
    }

    @Test
    fun `Given a city data model when map to domain model then return city domain model`() {
        val domainModel = mapper.mapToDomainModel(input = DataForTesting.cityDataModel)

        assertEquals(domainModel.cityId, DataForTesting.cityDataModel.cityId)
        assertEquals(domainModel.name, DataForTesting.cityDataModel.name)
    }

    @Test
    fun `Given an exception when parse to message then return string`() {
        val httpException = UnknownHostException()

        `when`(
            resources.getString(R.string.feature_forecast_server_error_message)
        ).thenReturn(DataForTesting.NETWORK_ERROR_MESSAGE)

        val message = mapper.parseExceptionToErrorMessage(httpException)

        assertEquals(message, DataForTesting.NETWORK_ERROR_MESSAGE)
    }

    private fun Long.toDisplayDateTime() =
        SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.getDefault()).format(this)

    companion object {
        private const val DATE_FORMAT_PATTERN = "EEE, dd MMM yyyy"
        private const val TODAY = "Today"
        private const val AVG_TEMPERATURE = "Avg: 20"
        private const val PRESSURE = "Pressure: 20"
        private const val HUMIDITY = "Humidity: 20"
        private const val DESCRIPTION = "Description: Rain"
    }
}
