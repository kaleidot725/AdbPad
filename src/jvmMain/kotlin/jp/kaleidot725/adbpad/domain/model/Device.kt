package jp.kaleidot725.adbpad.domain.model

data class Device(
    val serial: String,
    val state: DeviceState
)