package jp.kaleidot725.adbpad.domain.model.device

data class Device(
    val serial: String,
    val state: DeviceState,
)
