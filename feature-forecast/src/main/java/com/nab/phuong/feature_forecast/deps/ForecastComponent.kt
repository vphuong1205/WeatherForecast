package com.nab.phuong.feature_forecast.deps

import android.content.Context
import com.nab.phuong.core_network.NetworkConfig
import com.nab.phuong.core_network.client.NetworkClient
import com.nab.phuong.core_network.client.NetworkClientModule
import com.nab.phuong.feature_forecast.data.ForecastDataModule
import com.nab.phuong.feature_forecast.domain.ForecastDomainModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ForecastDataModule::class,
        ForecastDomainModule::class,
        NetworkClientModule::class
    ]
)
@Singleton
interface ForecastComponent : ForecastComponentDeps {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun networkConfig(networkConfig: NetworkConfig): Builder

        @BindsInstance
        fun networkClient(networkClient: NetworkClient): Builder

        fun build(): ForecastComponent
    }
}
