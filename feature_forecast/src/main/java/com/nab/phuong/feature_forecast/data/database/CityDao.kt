package com.nab.phuong.feature_forecast.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nab.phuong.feature_forecast.data.database.model.CityDataModel

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CityDataModel)

    @Query("SELECT * FROM city ORDER BY name DESC")
    suspend fun getAllCities(): List<CityDataModel>

    @Query("SELECT * FROM city WHERE name LIKE :cityName ORDER BY name DESC LIMIT 1")
    suspend fun getCityByName(cityName: String): CityDataModel?

}
