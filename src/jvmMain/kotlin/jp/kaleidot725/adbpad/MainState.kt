package jp.kaleidot725.adbpad

import com.malinskiy.adam.request.device.Device

data class MainState(
    val devices: List<Device> = emptyList(),
    val selectedDevice: Device? = null,
)