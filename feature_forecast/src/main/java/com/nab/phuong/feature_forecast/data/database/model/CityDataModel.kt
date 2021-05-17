package com.nab.phuong.feature_forecast.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nab.phuong.feature_forecast.data.database.model.CityDataModel.Companion.CITY_TABLE

@Entity(tableName = CITY_TABLE)
data class CityDataModel(
    @PrimaryKey
    @ColumnInfo(name = CITY_ID_COLUMN)
    val cityId: Long,
    @ColumnInfo(name = CITY_NAME_COLUMN)
    val name: String,
) {
    companion object {
        internal const val CITY_TABLE = "city"
        internal const val CITY_ID_COLUMN = "cityId"
        internal const val CITY_NAME_COLUMN = "name"
    }
}
