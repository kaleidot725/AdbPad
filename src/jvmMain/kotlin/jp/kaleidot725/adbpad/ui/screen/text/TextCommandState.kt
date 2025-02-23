package jp.kaleidot725.adbpad.ui.screen.text

import jp.kaleidot725.adbpad.core.mvi.MVIState
import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.model.device.Device

data class TextCommandState(
    val selectedCommand: TextCommand = TextCommand(""),
    val commands: List<TextCommand> = emptyList(),
    val userInputText: String = "",
    val isSendingUserInputText: Boolean = false,
    val selectedDevice: Device? = null,
    val isSendingTab: Boolean = false,
    val searchText: String = "",
) : MVIState {
    val canSendCommand: Boolean get() = selectedDevice != null
    val canSendInputText: Boolean get() = selectedDevice != null && userInputText.isNotEmpty()
    val canSaveInputText: Boolean get() = userInputText.isNotEmpty()
    val canSendTabKey: Boolean get() = selectedDevice != null
}
