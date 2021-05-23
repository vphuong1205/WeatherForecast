package com.nab.phuong.feature_forecast.presentation.model

import com.nab.phuong.feature_forecast.domain.model.City

sealed class CityState {
    class ListData(val data: List<City>) : CityState()
}
