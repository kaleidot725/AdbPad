package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.domain.model.Dialog
import jp.kaleidot725.adbpad.domain.model.setting.Appearance
import jp.kaleidot725.adbpad.domain.model.setting.WindowSize

data class MainState(
    val appearance: Appearance = Appearance.DARK,
    val size: WindowSize = WindowSize.UNKNOWN,
    val dialog: Dialog? = null
)
