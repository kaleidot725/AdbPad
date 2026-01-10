package jp.kaleidot725.adbpad.ui.screen.command

import jp.kaleidot725.adbpad.core.mvi.MVIBase
import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandCategory
import jp.kaleidot725.adbpad.domain.repository.NormalCommandOutputRepository
import jp.kaleidot725.adbpad.domain.usecase.command.ExecuteCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.command.GetNormalCommandGroup
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.ui.screen.command.model.CommandLayoutMode
import jp.kaleidot725.adbpad.ui.screen.command.state.CommandAction
import jp.kaleidot725.adbpad.ui.screen.command.state.CommandSideEffect
import jp.kaleidot725.adbpad.ui.screen.command.state.CommandState
import kotlinx.coroutines.launch

class CommandStateHolder(
    private val getNormalCommandGroup: GetNormalCommandGroup,
    private val executeCommandUseCase: ExecuteCommandUseCase,
    private val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase,
    private val normalCommandOutputRepository: NormalCommandOutputRepository,
) : MVIBase<CommandState, CommandAction, CommandSideEffect>(initialUiState = CommandState()) {
    override fun onSetup() {
        coroutineScope.launch {
            getSelectedDeviceFlowUseCase().collect {
                update { this.copy(selectedDevice = it) }
            }
        }
        coroutineScope.launch {
            val commands = getNormalCommandGroup()
            update { this.copy(commands = commands) }
        }
        coroutineScope.launch {
            normalCommandOutputRepository.getExecutionHistoryFlow().collect { history ->
                update { this.copy(executionHistory = history) }
            }
        }
    }

    override fun onRefresh() {
        coroutineScope.launch {
            val commands = getNormalCommandGroup()
            update { this.copy(commands = commands) }
        }
    }

    override fun onAction(uiAction: CommandAction) {
        coroutineScope.launch {
            when (uiAction) {
                is CommandAction.ClickCategoryTab -> clickTab(uiAction.category)
                is CommandAction.ExecuteCommand -> executeCommand(uiAction.command)
                is CommandAction.ToggleLayoutMode -> toggleLayoutMode()
                is CommandAction.SelectCommand -> selectCommand(uiAction.command)
            }
        }
    }

    private suspend fun executeCommand(command: NormalCommand) {
        val selectedDevice = state.value.selectedDevice ?: return
        executeCommandUseCase(
            device = selectedDevice,
            command = command,
            onStart = {
                normalCommandOutputRepository.clear()
                update {
                    this.copy(commands = getNormalCommandGroup())
                }
            },
            onFailed = {
                update {
                    this.copy(commands = getNormalCommandGroup())
                }
            },
            onComplete = {
                update {
                    this.copy(commands = getNormalCommandGroup())
                }
            },
        )
    }

    private fun clickTab(filtered: NormalCommandCategory) {
        update {
            this.copy(filtered = filtered)
        }
    }

    private fun toggleLayoutMode() {
        update {
            val newMode =
                when (layoutMode) {
                    CommandLayoutMode.CARD -> CommandLayoutMode.LIST
                    CommandLayoutMode.LIST -> CommandLayoutMode.CARD
                }
            this.copy(layoutMode = newMode)
        }
    }

    private fun selectCommand(command: NormalCommand?) {
        update {
            this.copy(selectedCommandId = command?.let { it::class.simpleName })
        }
    }
}
