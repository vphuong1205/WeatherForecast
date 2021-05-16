package com.nab.phuong.core_network.client

import com.nab.phuong.core_network.NetworkConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkClientImpl @Inject constructor(
    override val config: NetworkConfig,
) : NetworkClient {

    override val retrofit: Retrofit by lazy {
        retrofitInternal
    }

    override val okHttpClient: OkHttpClient by lazy {
        okHttpClientInternal
    }

    override fun <T> create(service: Class<T>): T {
        return retrofitInternal.create(service)
    }

    private val retrofitInternal: Retrofit by lazy {
        setupRetrofitBuilder()
    }

    private val okHttpClientInternal: OkHttpClient by lazy {
        setupOkHttp()
    }

    private fun setupRetrofitBuilder(): Retrofit {
        val builder = Retrofit.Builder().client(okHttpClientInternal)
        config.converters.forEach { builder.addConverterFactory(it) }
        return builder.baseUrl(config.baseUrl).build()
    }

    private fun setupOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(NETWORK_TIMEOUT_LONG, TimeUnit.SECONDS)
            .connectTimeout(NETWORK_TIMEOUT_LONG, TimeUnit.SECONDS)
            .build()
    }

    companion object {
        const val NETWORK_TIMEOUT_LONG = 30L
    }
}
