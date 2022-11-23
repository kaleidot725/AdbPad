package jp.kaleidot725.adbpad.view.screen.setting

data class SettingState(
    val adbDirectoryPath: String = "",
    val adbPortNumber: String = "",
    val sdkAndroidDirectoryPath: String = ""
) {
    val isValidAdbDirectoryPath: Boolean = adbDirectoryPath.isNotEmpty()
    val isValidAdbPortNumber: Boolean = adbPortNumber.toIntOrNull() != null
    val isValidSdkAndroidDirectoryPath: Boolean = sdkAndroidDirectoryPath.isNotEmpty()

    val canSave: Boolean
        get() {
            return isValidAdbDirectoryPath && isValidAdbPortNumber && isValidSdkAndroidDirectoryPath
        }
}
