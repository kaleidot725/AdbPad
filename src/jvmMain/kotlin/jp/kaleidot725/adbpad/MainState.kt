package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.core.mvi.MVIState
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.setting.WindowSize
import jp.kaleidot725.adbpad.domain.model.setting.AccentColor

data class MainState(
    val language: Language.Type = Language.Type.ENGLISH,
    val isDark: Boolean? = null,
    val size: WindowSize = WindowSize.UNKNOWN,
    val dialog: MainDialog = MainDialog.Empty,
    val category: MainCategory = MainCategory.Command,
    val selectedDevice: Device? = null,
    val isAlwaysOnTop: Boolean = false,
    val accentColor: AccentColor = AccentColor.BLUE,
) : MVIState
