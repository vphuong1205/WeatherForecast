package com.nab.phuong.feature_forecast.domain

import com.nab.phuong.feature_forecast.domain.usecase.ForecastUseCase
import com.nab.phuong.feature_forecast.domain.usecase.ForecastUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ForecastDomainModule {

    @Binds
    abstract fun bindsForecastUseCase(forecastUseCaseImpl: ForecastUseCaseImpl): ForecastUseCase
}
