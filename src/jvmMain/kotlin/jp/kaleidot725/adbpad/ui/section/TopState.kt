package jp.kaleidot725.adbpad.ui.section

import jp.kaleidot725.adbpad.domain.model.device.Device

data class TopState(
    val devices: List<Device> = emptyList(),
    val selectedDevice: Device? = null,
)
