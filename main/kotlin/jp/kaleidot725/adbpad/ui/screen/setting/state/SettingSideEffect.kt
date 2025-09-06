package jp.kaleidot725.adbpad.ui.screen.setting.state

import jp.kaleidot725.adbpad.core.mvi.MVISideEffect

sealed class SettingSideEffect : MVISideEffect {
    data object Saved : SettingSideEffect()
}
