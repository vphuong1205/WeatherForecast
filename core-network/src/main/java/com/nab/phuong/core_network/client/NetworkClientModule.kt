package com.nab.phuong.core_network.client

import dagger.Binds
import dagger.Module

@Module
abstract class NetworkClientModule {
    @Binds
    abstract fun binds(networkClientImpl: NetworkClientImpl): NetworkClient
}
