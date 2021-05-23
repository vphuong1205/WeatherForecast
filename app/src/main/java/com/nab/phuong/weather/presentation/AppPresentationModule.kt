package com.nab.phuong.weather.presentation

import androidx.lifecycle.ViewModel
import com.nab.phuong.core_viewmodel.ViewModelKey
import com.nab.phuong.core_viewmodel.ViewModelModule
import com.nab.phuong.weather.device.DeviceModule
import com.nab.phuong.weather.presentation.viewmodel.HostViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class, DeviceModule::class])
abstract class AppPresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(HostViewModel::class)
    abstract fun bindsForecastViewModel(hostViewModel: HostViewModel): ViewModel
}
