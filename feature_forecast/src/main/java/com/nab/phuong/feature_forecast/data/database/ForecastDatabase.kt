package com.nab.phuong.feature_forecast.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nab.phuong.feature_forecast.data.database.ForecastDatabase.Companion.FORECAST_DATABASE_VERSION
import com.nab.phuong.feature_forecast.data.database.model.CityDataModel
import com.nab.phuong.feature_forecast.data.database.model.ForecastDataModel

@Database(
    entities = [CityDataModel::class, ForecastDataModel::class],
    version = FORECAST_DATABASE_VERSION,
    exportSchema = false
)
abstract class ForecastDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    abstract fun forecastDao(): ForecastDao

    companion object {
        const val FORECAST_DATABASE_VERSION = 1
        private const val FORECAST_DATABASE_NAME = "forecast.db"

        fun build(context: Context): ForecastDatabase {
            return Room.databaseBuilder(
                context,
                ForecastDatabase::class.java,
                FORECAST_DATABASE_NAME
            ).build()
        }
    }
}
