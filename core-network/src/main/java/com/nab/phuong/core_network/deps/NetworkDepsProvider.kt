package com.nab.phuong.core_network.deps

import com.nab.phuong.core_network.NetworkConfig
import com.nab.phuong.core_network.client.NetworkClient

interface NetworkDepsProvider {
    val config: NetworkConfig
    val networkClient: NetworkClient
}
