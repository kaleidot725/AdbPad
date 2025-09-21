package jp.kaleidot725.adbpad.ui.section.right.state

import jp.kaleidot725.adbpad.core.mvi.MVIState
import jp.kaleidot725.adbpad.domain.model.device.Device

data class RightState(
    val selectedDevice: Device? = null,
    val isScrcpyControlVisible: Boolean = false,
) : MVIState