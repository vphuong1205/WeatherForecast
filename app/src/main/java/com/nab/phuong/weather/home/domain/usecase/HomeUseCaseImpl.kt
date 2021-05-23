package com.nab.phuong.weather.home.domain.usecase

import com.nab.phuong.weather.home.domain.model.DeviceInfo
import com.nab.phuong.weather.home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) :
    HomeUseCase {

    override fun getDeviceInfo(): DeviceInfo {
        return homeRepository.getDeviceInfo()
    }
}
