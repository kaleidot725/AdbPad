package jp.kaleidot725.adbpad

import com.malinskiy.adam.interactor.StartAdbInteractor
import jp.kaleidot725.adbpad.model.data.Command
import jp.kaleidot725.adbpad.model.usecase.ExecuteCommandUseCase
import jp.kaleidot725.adbpad.model.usecase.GetCommandListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainStateHolder(
    val getCommandListUseCase: GetCommandListUseCase = GetCommandListUseCase(),
    val executeCommandUseCase: ExecuteCommandUseCase = ExecuteCommandUseCase(),
) {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val commands: MutableStateFlow<List<Command>> = MutableStateFlow(emptyList())
    val state: StateFlow<MainState> = commands.map { commands ->
        MainState(commands = commands)
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), MainState())

    fun setup() {
        coroutineScope.launch {
            StartAdbInteractor()
        }
        commands.value = getCommandListUseCase()
    }

    fun executeCommand(command: Command) {
        coroutineScope.launch {
            executeCommandUseCase("emulator-5554", command)
        }
    }

    fun dispose() {
        coroutineScope.cancel()
    }
}
