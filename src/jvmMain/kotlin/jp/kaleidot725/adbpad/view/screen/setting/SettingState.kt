package jp.kaleidot725.adbpad.view.screen.setting

import jp.kaleidot725.adbpad.domain.model.setting.Appearance

data class SettingState(
    val appearance: Appearance = Appearance.DARK,
    val adbDirectoryPath: String = "",
    val adbPortNumber: String = "",
) {
    val isValidAdbDirectoryPath: Boolean = adbDirectoryPath.isNotEmpty()
    val isValidAdbPortNumber: Boolean = adbPortNumber.toIntOrNull() != null

    val canSave: Boolean
        get() {
            return isValidAdbDirectoryPath && isValidAdbPortNumber
        }
}
