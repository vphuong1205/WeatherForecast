package com.nab.phuong.weather.home.domain.model

data class DeviceInfo(
    val rooted: Boolean = DEFAULT_ROOTED_STATUS
) {
    companion object {
        private const val DEFAULT_ROOTED_STATUS = false
    }
}
