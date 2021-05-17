package com.nab.phuong.feature_forecast.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nab.phuong.feature_forecast.data.database.model.ForecastDataModel

@Dao
interface ForecastDao {
    @Query("SELECT * FROM forecast WHERE date >= :dateTime AND cityId like :cityId ORDER BY date DESC LIMIT :limitDays")
    suspend fun getForecastByCity(
        cityId: String,
        limitDays: Int,
        dateTime: Long
    ): List<ForecastDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecasts(forecasts: List<ForecastDataModel>)

    @Query("DELETE FROM forecast")
    fun deleteForecasts()
}
