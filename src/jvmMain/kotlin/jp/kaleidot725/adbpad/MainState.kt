package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.domain.model.setting.WindowSize

data class MainState(
    val size: WindowSize = WindowSize.UNKNOWN
)
