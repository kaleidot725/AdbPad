package jp.kaleidot725.adbpad.view.screen.text

import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.AddTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.DeleteTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.ExecuteTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.GetTextCommandUseCase
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

class TextCommandStateHolder(
    private val addTextCommandUseCase: AddTextCommandUseCase,
    private val deleteTextCommandUseCase: DeleteTextCommandUseCase,
    private val getTextCommandUseCase: GetTextCommandUseCase,
    private val executeTextCommandUseCase: ExecuteTextCommandUseCase,
    private val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase
) : ChildStateHolder<TextCommandState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val commands: MutableStateFlow<List<TextCommand>> = MutableStateFlow(emptyList())
    private val userInputText: MutableStateFlow<String> = MutableStateFlow("")
    private val selectedDevice: StateFlow<Device?> = getSelectedDeviceFlowUseCase()
        .stateIn(coroutineScope, SharingStarted.WhileSubscribed(), null)

    override val state: StateFlow<TextCommandState> = combine(
        commands,
        userInputText,
        selectedDevice
    ) { inputTexts, userInputText, selectedDevice ->
        TextCommandState(inputTexts, userInputText, selectedDevice)
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), TextCommandState())

    override fun setup() {
        coroutineScope.launch {
            commands.value = getTextCommandUseCase()
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

    fun sendCommand(command: TextCommand) {
        val selectedDevice = state.value.selectedDevice ?: return
        coroutineScope.launch {
            executeTextCommandUseCase(
                device = selectedDevice,
                command = command,
                onStart = { commands.value = getTextCommandUseCase() },
                onFailed = { commands.value = getTextCommandUseCase() },
                onComplete = { commands.value = getTextCommandUseCase() }
            )
        }
    }

    fun sendInputText() {
        val selectedDevice = state.value.selectedDevice ?: return
        coroutineScope.launch {
            executeTextCommandUseCase(
                device = selectedDevice,
                command = TextCommand(state.value.inputText),
                onStart = { commands.value = getTextCommandUseCase() },
                onFailed = { commands.value = getTextCommandUseCase() },
                onComplete = { commands.value = getTextCommandUseCase() }
            )
        }
    }

    fun saveInputText() {
        coroutineScope.launch {
            addTextCommandUseCase(state.value.inputText)
            commands.value = getTextCommandUseCase()
        }
    }

    fun deleteInputText(command: TextCommand) {
        coroutineScope.launch {
            deleteTextCommandUseCase(command)
            commands.value = getTextCommandUseCase()
        }
    }
}
