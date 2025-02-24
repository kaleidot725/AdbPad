package jp.kaleidot725.adbpad.ui.screen.text

import jp.kaleidot725.adbpad.core.mvi.MVI
import jp.kaleidot725.adbpad.core.mvi.mvi
import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.repository.TextCommandRepository
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.ExecuteTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.GetTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.SendTabCommandUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class TextCommandStateHolder(
    private val textCommandRepository: TextCommandRepository,
    private val getTextCommandUseCase: GetTextCommandUseCase,
    private val executeTextCommandUseCase: ExecuteTextCommandUseCase,
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
            update { this.copy(commands = commands) }
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
                is TextCommandAction.DeleteSelectedCommandText -> {
                    deleteInputText()
                }

                is TextCommandAction.SendTabCommand -> {
                    sendTabCommand()
                }

                is TextCommandAction.SendTextCommand -> {
                    sendTextCommand()
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
                    addNewTextCommand()
                }

                is TextCommandAction.UpdateSearchText -> {
                    updateSearchText(uiAction.text)
                }

                is TextCommandAction.UpdateCommandText -> {
                    updateTextCommandValue(uiAction.id, uiAction.value)
                }

                is TextCommandAction.UpdateCommandTitle -> {
                    updateTextCommandTitle(uiAction.id, uiAction.value)
                }
            }
        }
    }

    private suspend fun updateSearchText(searchText: String) {
        val commands = getTextCommandUseCase()
        update {
            copy(
                searchText = searchText,
                commands = commands.filter { it.title.startsWith(searchText) },
            )
        }
    }

    private val ascii = (0..255).map { it.toChar() }

    private suspend fun updateTextCommandValue(
        id: String,
        value: String,
    ) {
        val isAscii = value.none { it !in ascii }
        if (isAscii) {
            textCommandRepository.updateTextCommandValue(id, value)
            val commands = getTextCommandUseCase()
            update { copy(commands = commands) }
        }
    }

    private suspend fun updateTextCommandTitle(
        id: String,
        value: String,
    ) {
        textCommandRepository.updateTextCommandTitle(id, value)
        val commands = getTextCommandUseCase()
        update { copy(commands = commands) }
    }

    private suspend fun sendTextCommand() {
        val selectedDevice = currentState.selectedDevice ?: return
        val selectedCommand = currentState.selectedCommand ?: return
        executeTextCommandUseCase(
            device = selectedDevice,
            command = selectedCommand,
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

    private suspend fun addNewTextCommand() {
        val command =
            TextCommand(
                title = "",
                text = "",
            )
        textCommandRepository.addTextCommand(command)
        val commands = getTextCommandUseCase()
        val commandIndex = commands.indexOf(command)
        update {
            copy(
                commands = commands,
                selectedCommandIndex = commandIndex,
            )
        }
    }

    private suspend fun deleteInputText() {
        val selectedCommand = currentState.selectedCommand ?: return
        val selectedCommandIndex = currentState.commands.indexOf(selectedCommand)
        textCommandRepository.removeTextCommand(selectedCommand)

        val commands = getTextCommandUseCase()
        val newSelectedCommand = commands.getOrNull(selectedCommandIndex)
        val newSelectedCommandIndex = if (newSelectedCommand == null) commands.lastIndex else selectedCommandIndex
        update {
            copy(
                commands = commands,
                selectedCommandIndex = newSelectedCommandIndex,
            )
        }
    }

    private fun nextCommand() {
        val nextIndex = currentState.commands.indexOf(currentState.selectedCommand) + 1
        update { copy(selectedCommandIndex = nextIndex) }
    }

    private fun previousCommand() {
        val previousIndex = currentState.commands.indexOf(currentState.selectedCommand) - 1
        update { copy(selectedCommandIndex = previousIndex) }
    }

    private fun selectCommand(command: TextCommand) {
        val index = currentState.commands.indexOf(command)
        update { copy(selectedCommandIndex = index) }
    }
}
