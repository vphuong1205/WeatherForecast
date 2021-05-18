package com.nab.phuong.feature_forecast.data.mapper

import com.nab.phuong.feature_forecast.data.database.model.CityDataModel
import com.nab.phuong.feature_forecast.data.database.model.ForecastDataModel
import com.nab.phuong.feature_forecast.data.network.model.CityNetworkModel
import com.nab.phuong.feature_forecast.data.network.model.ForecastCityClusterModel
import com.nab.phuong.feature_forecast.domain.model.City
import com.nab.phuong.feature_forecast.domain.model.Forecast
import com.nab.phuong.feature_forecast.data.network.response.ForecastApiResponse

interface ForecastMapper {

    fun mapToDatabaseModel(input: CityNetworkModel): CityDataModel

    fun mapToDatabaseModel(input: ForecastApiResponse): List<ForecastDataModel>

    fun mapToDomainModel(input: ForecastDataModel): Forecast

    fun mapToDomainModel(input: CityDataModel): City

    fun mapToDatabaseModel(input: ForecastCityClusterModel): ForecastDataModel
}
