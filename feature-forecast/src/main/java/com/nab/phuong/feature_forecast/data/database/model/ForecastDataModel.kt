package com.nab.phuong.feature_forecast.data.database.model

import androidx.room.Entity
import com.nab.phuong.feature_forecast.data.database.model.CityDataModel.Companion.CITY_ID_COLUMN
import com.nab.phuong.feature_forecast.data.database.model.ForecastDataModel.Companion.FORECAST_FIELD_DATE
import com.nab.phuong.feature_forecast.data.database.model.ForecastDataModel.Companion.FORECAST_TABLE_NAME

@Entity(
    tableName = FORECAST_TABLE_NAME,
    primaryKeys = [CITY_ID_COLUMN, FORECAST_FIELD_DATE]
)
data class ForecastDataModel(
    val cityId: Long,
    val date: Long,
    val minTemperature: Double,
    val maxTemperature: Double,
    val pressure: Int,
    val humidity: Int,
    val description: String
) {
    companion object {
        internal const val FORECAST_TABLE_NAME = "forecast"
        internal const val FORECAST_FIELD_DATE = "date"
    }
}
