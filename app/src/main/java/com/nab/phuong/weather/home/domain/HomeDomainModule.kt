package com.nab.phuong.weather.home.domain

import com.nab.phuong.weather.home.domain.usecase.HomeUseCase
import com.nab.phuong.weather.home.domain.usecase.HomeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeDomainModule {

    @Binds
    abstract fun bindsHomeUseCase(homeUseCaseImpl: HomeUseCaseImpl): HomeUseCase
}
