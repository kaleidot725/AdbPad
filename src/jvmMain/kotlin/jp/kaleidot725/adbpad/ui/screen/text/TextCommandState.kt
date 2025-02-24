package jp.kaleidot725.adbpad.ui.screen.text

import jp.kaleidot725.adbpad.core.mvi.MVIState
import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.model.device.Device

data class TextCommandState(
    val selectedCommandIndex: Int? = null,
    val commands: List<TextCommand> = emptyList(),
    val userInputText: String = "",
    val isSendingUserInputText: Boolean = false,
    val selectedDevice: Device? = null,
    val isSendingTab: Boolean = false,
    val searchText: String = "",
) : MVIState {
    val selectedCommand: TextCommand? = commands.getOrNull(selectedCommandIndex ?: 0)
    val canSend: Boolean = selectedDevice != null
}
