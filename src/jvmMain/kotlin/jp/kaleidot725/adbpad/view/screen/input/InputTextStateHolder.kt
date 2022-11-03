package jp.kaleidot725.adbpad.view.screen.input

import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.input.AddInputTextUseCase
import jp.kaleidot725.adbpad.domain.usecase.input.DeleteInputTextUseCase
import jp.kaleidot725.adbpad.domain.usecase.input.ExecuteInputTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.input.GetInputTextUseCase
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
    private val addInputTextUseCase: AddInputTextUseCase,
    private val deleteInputTextUseCase: DeleteInputTextUseCase,
    private val getInputTextUseCase: GetInputTextUseCase,
    private val executeInputTextCommandUseCase: ExecuteInputTextCommandUseCase,
    private val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase
) : ChildStateHolder<InputTextState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val inputTexts: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private val userInputText: MutableStateFlow<String> = MutableStateFlow("")
    private val selectedDevice: StateFlow<Device?> = getSelectedDeviceFlowUseCase()
        .stateIn(coroutineScope, SharingStarted.WhileSubscribed(), null)

    override val state: StateFlow<InputTextState> = combine(
        inputTexts,
        userInputText,
        selectedDevice
    ) { inputTexts, userInputText, selectedDevice ->
        InputTextState(inputTexts, userInputText, selectedDevice)
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), InputTextState())

    override fun setup() {
        coroutineScope.launch {
            inputTexts.value = getInputTextUseCase()
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

    fun sendInputText(text: String) {
        val selectedDevice = state.value.selectedDevice ?: return
        coroutineScope.launch {
            executeInputTextCommandUseCase(selectedDevice.serial, text)
        }
    }

    fun saveInputText(text: String) {
        coroutineScope.launch {
            addInputTextUseCase(text)
            inputTexts.value = getInputTextUseCase()
        }
    }

    fun deleteInputText(text: String) {
        coroutineScope.launch {
            deleteInputTextUseCase(text)
            inputTexts.value = getInputTextUseCase()
        }
    }
}
