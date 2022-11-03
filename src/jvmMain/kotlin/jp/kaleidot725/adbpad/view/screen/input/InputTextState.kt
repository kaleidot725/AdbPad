package jp.kaleidot725.adbpad.view.screen.input

import jp.kaleidot725.adbpad.domain.model.Device

data class InputTextState(
    val inputTexts: List<String> = emptyList(),
    val userInputText: String = "",
    val selectedDevice: Device? = null
) {
    val canSendUserInputText: Boolean get() = userInputText.isNotEmpty()
    val canSaveUserInputText: Boolean get() = userInputText.isNotEmpty()
}
