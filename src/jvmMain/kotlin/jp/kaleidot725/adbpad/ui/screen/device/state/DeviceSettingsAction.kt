package jp.kaleidot725.adbpad.ui.screen.device.state

import jp.kaleidot725.adbpad.core.mvi.MVIAction
import jp.kaleidot725.adbpad.domain.model.device.DeviceSettings

sealed class DeviceSettingsAction : MVIAction {
    data class UpdateSettings(val settings: DeviceSettings) : DeviceSettingsAction()

    data object Save : DeviceSettingsAction()

    data object Cancel : DeviceSettingsAction()
}
