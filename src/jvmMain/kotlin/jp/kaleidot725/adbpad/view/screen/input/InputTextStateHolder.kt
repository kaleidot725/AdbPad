package jp.kaleidot725.adbpad.view.screen.input

import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.model.InputTextCommand
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.input.AddInputTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.input.DeleteInputTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.input.ExecuteInputTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.input.GetInputTextCommandUseCase
import jp.kaleidot725.adbpad.view.common.ChildStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class InputTextStateHolder(
    private val addInputTextCommandUseCase: AddInputTextCommandUseCase,
    private val deleteInputTextCommandUseCase: DeleteInputTextCommandUseCase,
    private val getInputTextCommandUseCase: GetInputTextCommandUseCase,
    private val executeInputTextCommandUseCase: ExecuteInputTextCommandUseCase,
    private val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase
) : ChildStateHolder<InputTextState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val commands: MutableStateFlow<List<InputTextCommand>> = MutableStateFlow(emptyList())
    private val userInputText: MutableStateFlow<String> = MutableStateFlow("")
    private val selectedDevice: StateFlow<Device?> = getSelectedDeviceFlowUseCase()
        .stateIn(coroutineScope, SharingStarted.WhileSubscribed(), null)

    override val state: StateFlow<InputTextState> = combine(
        commands,
        userInputText,
        selectedDevice
    ) { inputTexts, userInputText, selectedDevice ->
        InputTextState(inputTexts, userInputText, selectedDevice)
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), InputTextState())

    override fun setup() {
        coroutineScope.launch {
            commands.value = getInputTextCommandUseCase()
        }
    }

    override fun dispose() {
        coroutineScope.cancel()
    }

    fun updateInputText(text: String) {
        val isLettersOrDigits = text.none {
            it !in 'A'..'Z' && it !in 'a'..'z' && it !in '0'..'9'
        }
        if (isLettersOrDigits) this.userInputText.value = text
    }

    fun sendCommand(command: InputTextCommand) {
        val selectedDevice = state.value.selectedDevice ?: return
        coroutineScope.launch {
            executeInputTextCommandUseCase(
                device = selectedDevice,
                command = command,
                onStart = { commands.value = getInputTextCommandUseCase() },
                onFailed = { commands.value = getInputTextCommandUseCase() },
                onComplete = { commands.value = getInputTextCommandUseCase() }
            )
        }
    }

    fun sendInputText() {
        val selectedDevice = state.value.selectedDevice ?: return
        coroutineScope.launch {
            executeInputTextCommandUseCase(
                device = selectedDevice,
                command = InputTextCommand(state.value.inputText),
                onStart = { commands.value = getInputTextCommandUseCase() },
                onFailed = { commands.value = getInputTextCommandUseCase() },
                onComplete = { commands.value = getInputTextCommandUseCase() }
            )
        }
    }

    fun saveInputText() {
        coroutineScope.launch {
            addInputTextCommandUseCase(state.value.inputText)
            commands.value = getInputTextCommandUseCase()
        }
    }

    fun deleteInputText(command: InputTextCommand) {
        coroutineScope.launch {
            deleteInputTextCommandUseCase(command)
            commands.value = getInputTextCommandUseCase()
        }
    }
}
