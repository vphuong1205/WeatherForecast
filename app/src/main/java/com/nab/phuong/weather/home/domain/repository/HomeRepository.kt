package com.nab.phuong.weather.home.domain.repository

import com.nab.phuong.weather.home.domain.model.DeviceInfo

interface HomeRepository {

    fun getDeviceInfo(): DeviceInfo
}
