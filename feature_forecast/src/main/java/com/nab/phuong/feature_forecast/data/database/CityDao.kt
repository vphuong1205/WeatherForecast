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

    @Query("SELECT * FROM city WHERE name like '%' || :city || '%'|| '%' ORDER BY name DESC")
    suspend fun getCityByName(city: String? = EMPTY_STRING): List<CityDataModel>

    companion object {
        const val EMPTY_STRING = ""
    }
}
