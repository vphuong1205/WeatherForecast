package com.nab.phuong.weather.home.data.repository

import com.nab.phuong.weather.home.domain.model.DeviceInfo
import com.nab.phuong.weather.home.domain.repository.HomeRepository
import com.scottyab.rootbeer.RootBeer

class HomeRepositoryImpl constructor(private val rootBeer: RootBeer) : HomeRepository {

    override fun getDeviceInfo(): DeviceInfo {
        return DeviceInfo(rooted = rootBeer.isRooted)
    }
}
