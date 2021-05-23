package com.nab.phuong.weather.device

data class DeviceInfo(
    val rooted: Boolean = DEFAULT_ROOTED_STATUS
) {
    companion object {
        private const val DEFAULT_ROOTED_STATUS = false
    }
}
