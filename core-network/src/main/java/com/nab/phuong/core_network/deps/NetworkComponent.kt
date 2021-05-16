package com.nab.phuong.core_network.deps

import android.app.Application
import com.nab.phuong.core_network.client.NetworkClientModule
import com.nab.phuong.core_network.NetworkConfig
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkClientModule::class])
interface NetworkComponent : NetworkDepsProvider {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun networkConfig(networkConfig: NetworkConfig): Builder

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): NetworkComponent
    }
}
