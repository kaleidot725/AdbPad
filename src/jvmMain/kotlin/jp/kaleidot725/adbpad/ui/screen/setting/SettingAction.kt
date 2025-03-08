package jp.kaleidot725.adbpad.ui.screen.setting

import jp.kaleidot725.adbpad.core.mvi.MVIAction
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.setting.Appearance

sealed class SettingAction : MVIAction {
    data object Save : SettingAction()

    data class UpdateLanguage(
        val value: Language.Type,
    ) : SettingAction()

    data class UpdateAppearance(
        val value: Appearance,
    ) : SettingAction()

    data class UpdateAdbDirectoryPath(
        val value: String,
    ) : SettingAction()

    data class UpdateAdbPortNumberPath(
        val value: String,
    ) : SettingAction()
}
