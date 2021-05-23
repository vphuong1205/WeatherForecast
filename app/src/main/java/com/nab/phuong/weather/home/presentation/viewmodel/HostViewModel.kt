package com.nab.phuong.weather.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nab.phuong.weather.home.domain.usecase.HomeUseCase
import com.nab.phuong.weather.home.presentation.model.RootDeviceState
import javax.inject.Inject

class HostViewModel @Inject constructor(
    private val useCase: HomeUseCase
) : ViewModel() {
    private val _rootDeviceState = MutableLiveData<RootDeviceState>()
    val rootDeviceState: LiveData<RootDeviceState> = _rootDeviceState

    fun checkRootStatusInfo() {
        if (useCase.getDeviceInfo().rooted) {
            _rootDeviceState.postValue(RootDeviceState.RootedDevice)
        } else {
            _rootDeviceState.postValue(RootDeviceState.SecureDevice)
        }
    }
}
