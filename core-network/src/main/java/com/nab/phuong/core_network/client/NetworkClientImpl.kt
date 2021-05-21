package com.nab.phuong.core_network.client

import com.nab.phuong.core_network.NetworkConfig
import okhttp3.CertificatePinner
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
        val certificatePinnerBuilder = CertificatePinner.Builder()
        config.pinnerCertificates.forEach {
            certificatePinnerBuilder.add(config.pinnerHost, it)
        }

        return OkHttpClient.Builder()
            .readTimeout(config.readTimeout, TimeUnit.SECONDS)
            .writeTimeout(config.writeTimeout, TimeUnit.SECONDS)
            .connectTimeout(config.connectionTimeout, TimeUnit.SECONDS)
            .certificatePinner(certificatePinnerBuilder.build())
            .build()
    }

}
