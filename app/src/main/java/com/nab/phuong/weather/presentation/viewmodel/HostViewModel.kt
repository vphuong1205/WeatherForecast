package com.nab.phuong.weather.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nab.phuong.weather.device.DeviceInfo
import com.nab.phuong.weather.presentation.model.RootDeviceState
import javax.inject.Inject

class HostViewModel @Inject constructor(
    private val deviceInfo: DeviceInfo
) : ViewModel() {
    private val _rootDeviceState = MutableLiveData<RootDeviceState>()
    val rootDeviceState: LiveData<RootDeviceState> = _rootDeviceState

    fun checkRootStatusInfo() {
        if (deviceInfo.rooted) {
            _rootDeviceState.postValue(RootDeviceState.RootedDevice)
        } else {
            _rootDeviceState.postValue(RootDeviceState.SecureDevice)
        }
    }
}
