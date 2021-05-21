package com.nab.phuong.core_network

import okhttp3.Interceptor
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory

data class NetworkConfig(
    val connectionTimeout: Long = DEFAULT_NETWORK_TIMEOUT_LONG,
    val readTimeout: Long = DEFAULT_NETWORK_TIMEOUT_LONG,
    val writeTimeout: Long = DEFAULT_NETWORK_TIMEOUT_LONG,
    val baseUrl: String,
    val pinnerHost: String,
    val pinnerCertificates: List<String>,
    val interceptors: MutableList<Interceptor> = mutableListOf(),
    val converters: List<Converter.Factory> = listOf(DEFAULT_CONVERTER_FACTORY),
) {
    companion object {
        private val DEFAULT_CONVERTER_FACTORY: MoshiConverterFactory =
            MoshiConverterFactory.create()
        private const val DEFAULT_NETWORK_TIMEOUT_LONG = 30L
    }
}
