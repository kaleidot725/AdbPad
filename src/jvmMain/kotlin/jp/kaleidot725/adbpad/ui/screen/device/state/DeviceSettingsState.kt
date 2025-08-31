package jp.kaleidot725.adbpad.ui.screen.device.state

import jp.kaleidot725.adbpad.core.mvi.MVIState
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.device.DeviceSettings

data class DeviceSettingsState(
    val device: Device? = null,
    val deviceSettings: DeviceSettings? = null,
    val isLoaded: Boolean = false,
    val isSaving: Boolean = false,
) : MVIState