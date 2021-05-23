package com.nab.phuong.weather.home.presentation.model

sealed class RootDeviceState {
    object RootedDevice : RootDeviceState()
    object SecureDevice : RootDeviceState()
}
