package com.nab.phuong.core_network

import okhttp3.Interceptor
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory

data class NetworkConfig(
    val connectionTimeout: Long,
    val readTimeout: Long,
    val writeTimeout: Long,
    val baseUrl: String,
    val interceptors: MutableList<Interceptor> = mutableListOf(),
    val converters: List<Converter.Factory> = listOf(DEFAULT_CONVERTER_FACTORY),
) {
    companion object {
        private val DEFAULT_CONVERTER_FACTORY: MoshiConverterFactory =
            MoshiConverterFactory.create()
    }
}
