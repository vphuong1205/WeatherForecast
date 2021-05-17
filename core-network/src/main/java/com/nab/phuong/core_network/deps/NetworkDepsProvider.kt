package com.nab.phuong.core_network.deps

import com.nab.phuong.core_network.client.NetworkClient

interface NetworkDepsProvider {
    fun networkClient(): NetworkClient
}
