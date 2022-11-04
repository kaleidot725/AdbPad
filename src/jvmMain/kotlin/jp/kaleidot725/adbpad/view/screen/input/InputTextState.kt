package jp.kaleidot725.adbpad.view.screen.input

import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.model.InputTextCommand

data class InputTextState(
    val commands: List<InputTextCommand> = emptyList(),
    val inputText: String = "",
    val selectedDevice: Device? = null
) {
    val canSendCommand: Boolean get() = selectedDevice != null
    val canSendInputText: Boolean get() = selectedDevice != null && inputText.isNotEmpty()
    val canSaveInputText: Boolean get() = inputText.isNotEmpty()
}
