package com.nab.phuong.weather.presentation.deps

import android.content.Context
import com.nab.phuong.core_network.client.NetworkClient
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [PresentationViewModelModule::class, NetworkModule::class])
@Singleton
interface PresentationComponent : PresentationComponentDeps {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun networkClient(networkClient: NetworkClient): Builder

        fun build(): PresentationComponent
    }
}
