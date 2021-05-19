package com.nab.phuong.feature_forecast.presentation

import androidx.lifecycle.ViewModel
import com.nab.phuong.feature_forecast.domain.usecase.ForecastUseCase
import javax.inject.Inject

class ForecastViewModel @Inject constructor(
    private val forecastUseCase: ForecastUseCase
) : ViewModel() {

}
