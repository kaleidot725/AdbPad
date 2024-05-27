package jp.kaleidot725.adbpad.view.screen.setting

import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.setting.Appearance

data class SettingState(
    val languages: List<Language.Type> = Language.Type.values().toList(),
    val selectedLanguage: Language.Type = Language.Type.ENGLISH,
    val appearance: Appearance = Appearance.DARK,
    val adbDirectoryPath: String = "",
    val adbPortNumber: String = "",
    val isRestartingAdb: Boolean = false,
) {
    val isValidAdbDirectoryPath: Boolean = adbDirectoryPath.isNotEmpty()
    val isValidAdbPortNumber: Boolean = adbPortNumber.toIntOrNull() != null

    val canSave: Boolean
        get() {
            return isValidAdbDirectoryPath && isValidAdbPortNumber
        }
}
