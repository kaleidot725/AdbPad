package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.domain.model.Dialog
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.setting.WindowSize

data class MainState(
    val language: Language.Type = Language.Type.ENGLISH,
    val isDark: Boolean = false,
    val size: WindowSize = WindowSize.UNKNOWN,
    val dialog: Dialog? = null,
    val category: MainCategory = MainCategory.Command,
    val devices: List<Device> = emptyList(),
    val selectedDevice: Device? = null,
)
