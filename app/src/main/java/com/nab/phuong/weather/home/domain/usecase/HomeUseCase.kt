package com.nab.phuong.weather.home.domain.usecase

import com.nab.phuong.weather.home.domain.model.DeviceInfo

interface HomeUseCase {

    fun getDeviceInfo(): DeviceInfo
}
