package jp.kaleidot725.adbpad.ui.screen.text

import jp.kaleidot725.adbpad.core.mvi.MVI
import jp.kaleidot725.adbpad.core.mvi.mvi
import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.AddTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.DeleteTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.ExecuteTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.GetTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.SendTabCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.SendUserInputTextCommandUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class TextCommandStateHolder(
    private val addTextCommandUseCase: AddTextCommandUseCase,
    private val deleteTextCommandUseCase: DeleteTextCommandUseCase,
    private val getTextCommandUseCase: GetTextCommandUseCase,
    private val executeTextCommandUseCase: ExecuteTextCommandUseCase,
    private val sendUserInputTextCommandUseCase: SendUserInputTextCommandUseCase,
    private val sendTabCommandUseCase: SendTabCommandUseCase,
    private val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase,
) : MVI<TextCommandState, TextCommandAction, TextCommandSideEffect> by mvi(initialUiState = TextCommandState()) {
    override fun onSetup() {
        coroutineScope.launch {
            getSelectedDeviceFlowUseCase().collect {
                update { this.copy(selectedDevice = it) }
            }
        }
        coroutineScope.launch {
            val commands = getTextCommandUseCase()
            update {
                this.copy(
                    selectedCommand = commands.firstOrNull() ?: TextCommand(""),
                    commands = commands
                )
            }
        }
    }

    override fun onRefresh() {
        coroutineScope.launch {
            val commands = getTextCommandUseCase()
            update {
                this.copy(commands = commands)
            }
        }
    }

    override fun onDispose() {
        coroutineScope.cancel()
    }

    override fun onAction(uiAction: TextCommandAction) {
        coroutineScope.launch {
            when (uiAction) {
                is TextCommandAction.DeleteInputText -> {
                    deleteInputText(uiAction.command)
                }

                is TextCommandAction.SaveInputText -> {
                    saveInputText()
                }

                is TextCommandAction.SendInputText -> {
                    sendInputText()
                }

                is TextCommandAction.SendTabCommand -> {
                    sendTabCommand()
                }

                is TextCommandAction.SendTextCommand -> {
                    sendTextCommand(uiAction.command)
                }

                is TextCommandAction.UpdateInputText -> {
                    updateInputText(uiAction.text)
                }

                is TextCommandAction.NextCommand -> {
                    nextCommand()
                }

                is TextCommandAction.PreviousCommand -> {
                    previousCommand()
                }

                is TextCommandAction.SelectCommand -> {
                    selectCommand(uiAction.command)
                }

                TextCommandAction.AddNewText -> {
                    // TODO
                }

                is TextCommandAction.UpdateSearchText -> {
                    updateSearchText(uiAction.text)
                }
            }
        }
    }

    private suspend fun updateSearchText(text: String) {
        val commands = getTextCommandUseCase()
        update {
            copy(
                searchText = text,
                commands = commands.filter { it.text.startsWith(text) }
            )
        }
    }

    private val ascii = (0..255).map { it.toChar() }

    private fun updateInputText(text: String) {
        val isAscii = text.none { it !in ascii }
        if (isAscii) update { copy(userInputText = text) }
    }

    private suspend fun sendTextCommand(command: TextCommand) {
        val selectedDevice = currentState.selectedDevice ?: return
        executeTextCommandUseCase(
            device = selectedDevice,
            command = command,
            onStart = {
                val commands = getTextCommandUseCase()
                update { copy(commands = commands) }
            },
            onFailed = {
                val commands = getTextCommandUseCase()
                update { copy(commands = commands) }
            },
            onComplete = {
                val commands = getTextCommandUseCase()
                update { copy(commands = commands) }
            },
        )
    }

    private suspend fun sendTabCommand() {
        val selectedDevice = currentState.selectedDevice ?: return
        sendTabCommandUseCase(
            device = selectedDevice,
            onStart = {
                update { copy(isSendingTab = true) }
            },
            onFailed = {
                update { copy(isSendingTab = false) }
            },
            onComplete = {
                update { copy(isSendingTab = false) }
            },
        )
    }

    private suspend fun sendInputText() {
        val selectedDevice = currentState.selectedDevice ?: return
        sendUserInputTextCommandUseCase(
            device = selectedDevice,
            text = state.value.userInputText,
            onStart = {
                update { copy(isSendingUserInputText = true) }
            },
            onFailed = {
                update { copy(isSendingUserInputText = false) }
            },
            onComplete = {
                update { copy(isSendingUserInputText = false) }
            },
        )
    }

    private suspend fun saveInputText() {
        val userInputText = currentState.userInputText
        addTextCommandUseCase(userInputText)

        val commands = getTextCommandUseCase()
        update { copy(commands = commands) }
    }

    private suspend fun deleteInputText(command: TextCommand) {
        deleteTextCommandUseCase(command)
        val commands = getTextCommandUseCase()
        update { copy(commands = commands) }
    }

    private fun nextCommand() {
        val nextIndex = currentState.commands.indexOf(currentState.selectedCommand) + 1
        val nextCommand = currentState.commands.getOrNull(nextIndex) ?: return
        update { copy(selectedCommand = nextCommand) }
    }

    private fun previousCommand() {
        val nextIndex = currentState.commands.indexOf(currentState.selectedCommand) - 1
        val nextCommand = currentState.commands.getOrNull(nextIndex) ?: return
        update { copy(selectedCommand = nextCommand) }
    }

    private fun selectCommand(command: TextCommand) {
        update { copy(selectedCommand = command) }
    }
}
