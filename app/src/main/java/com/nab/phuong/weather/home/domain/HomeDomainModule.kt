package com.nab.phuong.weather.home.domain

import com.nab.phuong.weather.home.domain.usecase.HomeUseCase
import com.nab.phuong.weather.home.domain.usecase.HomeUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class HomeDomainModule {

    @Binds
    abstract fun bindsHomeUseCase(homeUseCaseImpl: HomeUseCaseImpl): HomeUseCase
}
