package com.nab.phuong.feature_forecast.presentation

import androidx.lifecycle.ViewModel
import com.nab.phuong.core_viewmodel.ViewModelKey
import com.nab.phuong.core_viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class ForecastPresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(ForecastViewModel::class)
    abstract fun buildGoPayHomeViewModel(foreCastViewModel: ForecastViewModel): ViewModel
}
