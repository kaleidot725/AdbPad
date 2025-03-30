package jp.kaleidot725.adbpad.ui.screen.device.state

import jp.kaleidot725.adbpad.core.mvi.MVIAction
import jp.kaleidot725.adbpad.domain.model.device.Device

sealed class DeviceAction : MVIAction {
    data class UpdateDeviceName(
        val device: Device,
        val name: String,
    ) : DeviceAction()

    data object Close : DeviceAction()

    data object Save : DeviceAction()
}
