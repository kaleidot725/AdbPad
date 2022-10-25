package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.model.data.Dialog
import jp.kaleidot725.adbpad.model.usecase.input.AddInputTextUseCase
import jp.kaleidot725.adbpad.model.usecase.input.DeleteInputTextUseCase
import jp.kaleidot725.adbpad.model.usecase.input.ExecuteInputTextCommandUseCase
import jp.kaleidot725.adbpad.model.usecase.input.GetInputTextUseCase
import jp.kaleidot725.adbpad.model.usecase.screenshot.TakeScreenshotUseCase
import jp.kaleidot725.adbpad.model.usecase.screenshot.TakeThemeScreenshotUseCase
import jp.kaleidot725.adbpad.view.common.StateHolder
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
import java.io.File

class MainStateHolder(

    val getInputTextUseCase: GetInputTextUseCase = GetInputTextUseCase(),
    val executeInputTextCommandUseCase: ExecuteInputTextCommandUseCase = ExecuteInputTextCommandUseCase(),
    val addInputTextUseCase: AddInputTextUseCase = AddInputTextUseCase(),
    val deleteInputTextUseCase: DeleteInputTextUseCase = DeleteInputTextUseCase(),
    val takeScreenshotUseCase: TakeScreenshotUseCase = TakeScreenshotUseCase(),
    val takeThemeScreenshotUseCase: TakeThemeScreenshotUseCase = TakeThemeScreenshotUseCase()
) : StateHolder<MainState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val inputTexts: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private val userInputText: MutableStateFlow<String> = MutableStateFlow("")
    private val previewImage1: MutableStateFlow<File?> = MutableStateFlow(null)
    private val previewImage2: MutableStateFlow<File?> = MutableStateFlow(null)
    private val dialog: MutableStateFlow<Dialog?> = MutableStateFlow(null)

    override val state: StateFlow<MainState> = combine(
        inputTexts,
        userInputText,
        previewImage1,
        previewImage2,
        dialog
    ) { inputTexts, userInputText, previewImage1, previewImage2, dialog ->
        MainState(
            inputTexts = inputTexts,
            userInputText = userInputText,
            imageFile1 = previewImage1,
            imageFile2 = previewImage2,
            dialog = dialog
        )
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), MainState())

    override fun setup() {
        coroutineScope.launch {
            inputTexts.value = getInputTextUseCase()
        }
    }

    fun inputText(text: String) {
        coroutineScope.launch {
            val serial = state.value.selectedDevice?.serial
            executeInputTextCommandUseCase(serial, text)
        }
    }

    fun updateInputText(text: String) {
        val isLettersOrDigits = text.none {
            it !in 'A'..'Z' && it !in 'a'..'z' && it !in '0'..'9'
        }
        if (isLettersOrDigits) this.userInputText.value = text
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

    fun takeScreenShot() {
        coroutineScope.launch {
            val serial = state.value.selectedDevice?.serial
            val filePath = takeScreenshotUseCase(serial) ?: return@launch
            previewImage1.value = File(filePath)
            previewImage2.value = null
        }
    }

    fun takeThemeScreenShot() {
        coroutineScope.launch {
            val serial = state.value.selectedDevice?.serial
            val filePathPair = takeThemeScreenshotUseCase(serial) ?: return@launch
            previewImage1.value = File(filePathPair.first)
            previewImage2.value = File(filePathPair.second)
        }
    }

    fun showSettingDialog() {
        dialog.value = Dialog.Setting
    }

    fun closeDialog() {
        dialog.value = null
    }

    override fun dispose() {
        coroutineScope.cancel()
    }

    interface Syncer {
        fun sync(state: MainState)
    }
}
