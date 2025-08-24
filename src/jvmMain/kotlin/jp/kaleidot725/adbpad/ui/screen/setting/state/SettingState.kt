package jp.kaleidot725.adbpad.ui.screen.setting.state

import jp.kaleidot725.adbpad.core.mvi.MVIState
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.setting.Appearance

data class SettingState(
    val initialized: Boolean = false,
    val languages: List<Language.Type> = Language.Type.entries,
    val selectedLanguage: Language.Type = Language.Type.ENGLISH,
    val appearance: Appearance = Appearance.DARK,
    val adbDirectoryPath: String = "",
    val adbPortNumber: String = "",
    val scrcpyBinaryPath: String = "",
    val isSaving: Boolean = false,
) : MVIState {
    val isValidAdbDirectoryPath: Boolean = adbDirectoryPath.isNotEmpty()
    val isValidAdbPortNumber: Boolean = adbPortNumber.toIntOrNull() != null
    val isValidScrcpyBinaryPath: Boolean = true

    val canSave: Boolean
        get() {
            return isValidAdbDirectoryPath && isValidAdbPortNumber && isValidScrcpyBinaryPath && !isSaving
        }

    val canCancel: Boolean
        get() {
            return !isSaving
        }
}
