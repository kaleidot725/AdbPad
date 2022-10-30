package jp.kaleidot725.adbpad.view.screen.input

import com.malinskiy.adam.request.device.Device
import jp.kaleidot725.adbpad.domain.model.Event
import jp.kaleidot725.adbpad.domain.usecase.input.AddInputTextUseCase
import jp.kaleidot725.adbpad.domain.usecase.input.DeleteInputTextUseCase
import jp.kaleidot725.adbpad.domain.usecase.input.ExecuteInputTextCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.input.GetInputTextUseCase
import jp.kaleidot725.adbpad.view.common.ChildStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class InputTextStateHolder(
    val addInputTextUseCase: AddInputTextUseCase,
    val deleteInputTextUseCase: DeleteInputTextUseCase,
    val getInputTextUseCase: GetInputTextUseCase,
    val executeInputTextCommandUseCase: ExecuteInputTextCommandUseCase
) : ChildStateHolder<InputTextState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val inputTexts: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private val userInputText: MutableStateFlow<String> = MutableStateFlow("")
    override val state: StateFlow<InputTextState> = combine(inputTexts, userInputText) { inputTexts, userInputText ->
        InputTextState(inputTexts, userInputText)
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), InputTextState())

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    override val event: SharedFlow<Event> = _event

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

    fun sendInputText(device: Device, text: String) {
        coroutineScope.launch {
            executeInputTextCommandUseCase(device.serial, text)
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
