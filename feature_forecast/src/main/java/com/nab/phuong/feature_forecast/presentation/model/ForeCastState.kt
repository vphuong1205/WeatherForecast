package com.nab.phuong.feature_forecast.presentation.model

import com.nab.phuong.feature_forecast.domain.model.Forecast

sealed class ForeCastState {
    object Loading : ForeCastState()
    class ListData(val data: List<Forecast>) : ForeCastState()
    class Error(val errorMessage: String) : ForeCastState()
    object Empty : ForeCastState()
}
