package jp.kaleidot725.adbpad

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.loadImageBitmap
import com.malinskiy.adam.request.device.Device
import jp.kaleidot725.adbpad.model.data.Command
import jp.kaleidot725.adbpad.model.data.InputText
import jp.kaleidot725.adbpad.model.usecase.AddInputTextUseCase
import jp.kaleidot725.adbpad.model.usecase.DeleteInputTextUseCase
import jp.kaleidot725.adbpad.model.usecase.ExecuteCommandUseCase
import jp.kaleidot725.adbpad.model.usecase.ExecuteInputTextUseCase
import jp.kaleidot725.adbpad.model.usecase.GetAndroidDevicesFlowUseCase
import jp.kaleidot725.adbpad.model.usecase.GetCommandListUseCase
import jp.kaleidot725.adbpad.model.usecase.GetInputTextUseCase
import jp.kaleidot725.adbpad.model.usecase.StartAdbUseCase
import jp.kaleidot725.adbpad.model.usecase.TakeScreenshotUseCase
import jp.kaleidot725.adbpad.model.usecase.TakeThemeScreenshotUseCase
import jp.kaleidot725.adbpad.view.resource.Menu
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
    val startAdbUseCase: StartAdbUseCase = StartAdbUseCase(),
    val getAndroidDevicesFlowUseCase: GetAndroidDevicesFlowUseCase = GetAndroidDevicesFlowUseCase(),
    val getCommandListUseCase: GetCommandListUseCase = GetCommandListUseCase(),
    val getInputTextUseCase: GetInputTextUseCase = GetInputTextUseCase(),
    val executeCommandUseCase: ExecuteCommandUseCase = ExecuteCommandUseCase(),
    val executeInputTextUseCase: ExecuteInputTextUseCase = ExecuteInputTextUseCase(),
    val addInputTextUseCase: AddInputTextUseCase = AddInputTextUseCase(),
    val deleteInputTextUseCase: DeleteInputTextUseCase = DeleteInputTextUseCase(),
    val takeScreenshotUseCase: TakeScreenshotUseCase = TakeScreenshotUseCase(),
    val takeThemeScreenshotUseCase: TakeThemeScreenshotUseCase = TakeThemeScreenshotUseCase()
) {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)

    private val menus: MutableStateFlow<List<Menu>> = MutableStateFlow(emptyList())
    private val selectedMenu: MutableStateFlow<Menu?> = MutableStateFlow(null)
    private val commands: MutableStateFlow<List<Command>> = MutableStateFlow(emptyList())
    private val devices: MutableStateFlow<List<Device>> = MutableStateFlow(emptyList())
    private val selectedDevice: MutableStateFlow<Device?> = MutableStateFlow(null)
    private val inputTexts: MutableStateFlow<List<InputText>> = MutableStateFlow(emptyList())
    private val userInputText: MutableStateFlow<InputText> = MutableStateFlow(InputText(""))
    private val previewImage1: MutableStateFlow<ImageBitmap?> = MutableStateFlow(null)
    private val previewImage2: MutableStateFlow<ImageBitmap?> = MutableStateFlow(null)

    val state: StateFlow<MainState> = combine(
        menus, selectedMenu, commands, devices, selectedDevice, inputTexts, userInputText, previewImage1, previewImage2
    ) {
        MainState(
            menus = it[0] as List<Menu>,
            selectedMenu = it[1] as Menu?,
            commands = it[2] as List<Command>,
            devices = it[3] as List<Device>,
            selectedDevice = it[4] as Device?,
            inputTexts = it[5] as List<InputText>,
            inputText = it[6] as InputText,
            previewImageUrl1 = it[7] as ImageBitmap?,
            previewImageUrl2 = it[8] as ImageBitmap?
        )
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), MainState())

    fun setup() {
        coroutineScope.launch {
            menus.value = Menu.values().toList()
            commands.value = getCommandListUseCase()
            inputTexts.value = getInputTextUseCase()
        }

        coroutineScope.launch {
            startAdbUseCase()
            getAndroidDevicesFlowUseCase(coroutineScope).collect {
                devices.value = it
                val hasNotDevice = !it.contains(selectedDevice.value)
                if (hasNotDevice) selectedDevice.value = it.firstOrNull()
            }
        }
    }

    fun selectDevice(device: Device) {
        selectedDevice.value = device
    }

    fun selectMenu(menu: Menu) {
        selectedMenu.value = menu
    }

    fun executeCommand(command: Command) {
        coroutineScope.launch {
            val serial = state.value.selectedDevice?.serial
            executeCommandUseCase(serial, command)
        }
    }

    fun inputText(inputText: InputText) {
        coroutineScope.launch {
            val serial = state.value.selectedDevice?.serial
            executeInputTextUseCase(serial, inputText)
        }
    }

    fun updateInputText(inputtedText: InputText) {
        val isLettersOrDigits = inputtedText.content.none {
            it !in 'A'..'Z' && it !in 'a'..'z' && it !in '0'..'9'
        }
        if (isLettersOrDigits) this.userInputText.value = inputtedText
    }

    fun saveInputText(inputText: InputText) {
        coroutineScope.launch {
            addInputTextUseCase(inputText)
            inputTexts.value = getInputTextUseCase()
        }
    }

    fun deleteInputText(inputText: InputText) {
        coroutineScope.launch {
            deleteInputTextUseCase(inputText)
            inputTexts.value = getInputTextUseCase()
        }
    }

    fun takeScreenShot() {
        coroutineScope.launch {
            val serial = state.value.selectedDevice?.serial
            val filePath = takeScreenshotUseCase(serial) ?: return@launch
            previewImage1.value = loadImageBitmap(File(filePath).inputStream())
            previewImage2.value = null
        }
    }

    fun takeThemeScreenShot() {
        coroutineScope.launch {
            val serial = state.value.selectedDevice?.serial
            val filePathPair = takeThemeScreenshotUseCase(serial) ?: return@launch
            previewImage1.value = loadImageBitmap(File(filePathPair.first).inputStream())
            previewImage2.value = loadImageBitmap(File(filePathPair.second).inputStream())
        }
    }

    fun dispose() {
        coroutineScope.cancel()
    }
}
