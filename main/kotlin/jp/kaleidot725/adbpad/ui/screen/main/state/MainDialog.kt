package jp.kaleidot725.adbpad.ui.screen.main.state

import jp.kaleidot725.adbpad.domain.model.device.Device

sealed class MainDialog {
    data object Setting : MainDialog()

    data class DeviceSettings(
        val device: Device,
    ) : MainDialog()

    data object AdbError : MainDialog()

    data object Empty : MainDialog()
}
