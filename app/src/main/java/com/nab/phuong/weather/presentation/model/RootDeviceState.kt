package com.nab.phuong.weather.presentation.model

sealed class RootDeviceState {
    object RootedDevice : RootDeviceState()
    object SecureDevice : RootDeviceState()
}
