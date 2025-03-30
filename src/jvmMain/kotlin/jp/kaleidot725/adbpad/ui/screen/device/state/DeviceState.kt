package jp.kaleidot725.adbpad.ui.screen.device.state

import jp.kaleidot725.adbpad.core.mvi.MVIState
import jp.kaleidot725.adbpad.domain.model.device.Device

data class DeviceState(
    val devices: List<Device>,
) : MVIState
