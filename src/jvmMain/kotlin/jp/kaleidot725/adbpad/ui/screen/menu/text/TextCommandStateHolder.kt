package jp.kaleidot725.adbpad.ui.screen.menu.text

import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.AddTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.DeleteTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.ExecuteTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.GetTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.SendTabCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.text.SendUserInputTextCommandUseCase
import jp.kaleidot725.adbpad.ui.common.ChildStateHolder
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
    private val sendUserInputTextCommandUseCase: SendUserInputTextCommandUseCase,
    private val sendTabCommandUseCase: SendTabCommandUseCase,
    private val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase,
) : ChildStateHolder<TextCommandState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val commands: MutableStateFlow<List<TextCommand>> = MutableStateFlow(emptyList())
    private val userInputText: MutableStateFlow<String> = MutableStateFlow("")
    private val isSending: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val isSendingTag: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val selectedDevice: StateFlow<Device?> =
        getSelectedDeviceFlowUseCase()
            .stateIn(coroutineScope, SharingStarted.WhileSubscribed(), null)

    override val state: StateFlow<TextCommandState> =
        combine(
            commands,
            userInputText,
            isSending,
            isSendingTag,
            selectedDevice,
        ) { inputTexts, userInputText, isSending, isSendingTag, selectedDevice ->
            TextCommandState(inputTexts, userInputText, isSending, selectedDevice, isSendingTag)
        }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), TextCommandState())

    override fun setup() {
        coroutineScope.launch {
            commands.value = getTextCommandUseCase()
        }
    }

    override fun refresh() {
        coroutineScope.launch {
            commands.value = getTextCommandUseCase()
        }
    }

    override fun dispose() {
        coroutineScope.cancel()
    }

    private val ascii = (0..255).map { it.toChar() }

    fun updateInputText(text: String) {
        val isAscii = text.none { it !in ascii }
        if (isAscii) this.userInputText.value = text
    }

    fun sendTextCommand(command: TextCommand) {
        val selectedDevice = state.value.selectedDevice ?: return
        coroutineScope.launch {
            executeTextCommandUseCase(
                device = selectedDevice,
                command = command,
                onStart = { commands.value = getTextCommandUseCase() },
                onFailed = { commands.value = getTextCommandUseCase() },
                onComplete = { commands.value = getTextCommandUseCase() },
            )
        }
    }

    fun sendInputText() {
        val selectedDevice = state.value.selectedDevice ?: return
        coroutineScope.launch {
            sendUserInputTextCommandUseCase(
                device = selectedDevice,
                text = state.value.userInputText,
                onStart = { isSending.value = true },
                onFailed = { isSending.value = false },
                onComplete = { isSending.value = false },
            )
        }
    }

    fun sendTabCommand() {
        val selectedDevice = state.value.selectedDevice ?: return
        coroutineScope.launch {
            sendTabCommandUseCase(
                device = selectedDevice,
                onStart = { isSendingTag.value = true },
                onFailed = { isSendingTag.value = false },
                onComplete = { isSendingTag.value = false },
            )
        }
    }

    fun saveInputText() {
        coroutineScope.launch {
            addTextCommandUseCase(state.value.userInputText)
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
