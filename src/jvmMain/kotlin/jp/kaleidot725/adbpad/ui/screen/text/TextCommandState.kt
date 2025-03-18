package jp.kaleidot725.adbpad.ui.screen.text

import jp.kaleidot725.adbpad.core.mvi.MVIState
import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.sort.SortType

data class TextCommandState(
    val selectedCommandId: String? = null,
    val commands: List<TextCommand> = emptyList(),
    val userInputText: String = "",
    val isSendingUserInputText: Boolean = false,
    val selectedDevice: Device? = null,
    val isSendingTab: Boolean = false,
    val searchText: String = "",
    val sortType: SortType = SortType.SORT_BY_NAME_ASC,
    val selectedTextCommandOption: TextCommand.Option = TextCommand.Option.SendWithTab,
) : MVIState {
    val selectedCommand: TextCommand? = commands.firstOrNull { it.id == selectedCommandId }
    val canSend: Boolean = selectedDevice != null
}
