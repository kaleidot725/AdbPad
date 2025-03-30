package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.core.mvi.MVIAction
import jp.kaleidot725.adbpad.domain.model.setting.WindowSize

sealed class MainAction : MVIAction {
    data class SaveSetting(
        val windowSize: WindowSize,
    ) : MainAction()

    data class ClickCategory(
        val category: MainCategory,
    ) : MainAction()

    data object OpenSetting : MainAction()

    data object OpenDevice : MainAction()
}
