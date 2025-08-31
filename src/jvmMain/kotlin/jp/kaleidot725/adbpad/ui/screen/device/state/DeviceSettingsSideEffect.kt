package jp.kaleidot725.adbpad.ui.screen.device.state

import jp.kaleidot725.adbpad.core.mvi.MVISideEffect

sealed class DeviceSettingsSideEffect : MVISideEffect {
    data object Saved : DeviceSettingsSideEffect()
    data object Cancelled : DeviceSettingsSideEffect()
}