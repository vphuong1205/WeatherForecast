package com.nab.phuong.feature_forecast.deps

import android.content.Context
import com.nab.phuong.core_network.client.NetworkClient
import com.nab.phuong.feature_forecast.data.ForecastDataModule
import com.nab.phuong.feature_forecast.presentation.ForecastPresentationModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ForecastPresentationModule::class, ForecastDataModule::class])
@Singleton
interface ForecastComponent : ForecastComponentDeps {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun networkClient(networkClient: NetworkClient): Builder

        fun build(): ForecastComponent
    }
}
