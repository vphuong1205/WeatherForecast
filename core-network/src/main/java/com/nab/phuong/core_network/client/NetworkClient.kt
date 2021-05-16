package com.nab.phuong.core_network.client

import com.nab.phuong.core_network.NetworkConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface NetworkClient {
    val config: NetworkConfig
    val retrofit: Retrofit
    val okHttpClient: OkHttpClient
    fun <T> create(service: Class<T>): T
}
