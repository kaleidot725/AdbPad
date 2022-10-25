package jp.kaleidot725.adbpad.view.screen.input

data class InputTextState(
    val inputTexts: List<String> = emptyList(),
    val userInputText: String = ""
) {
    val canSendUserInputText: Boolean get() = userInputText.isNotEmpty()
    val canSaveUserInputText: Boolean get() = userInputText.isNotEmpty()
}
