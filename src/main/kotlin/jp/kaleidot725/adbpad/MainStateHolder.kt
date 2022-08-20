package jp.kaleidot725.adbpad

import com.malinskiy.adam.request.device.Device
import jp.kaleidot725.adbpad.model.data.Command
import jp.kaleidot725.adbpad.model.data.InputText
import jp.kaleidot725.adbpad.model.usecase.ExecuteCommandUseCase
import jp.kaleidot725.adbpad.model.usecase.ExecuteInputTextUseCase
import jp.kaleidot725.adbpad.model.usecase.GetAndroidDeviceListUseCase
import jp.kaleidot725.adbpad.model.usecase.GetCommandListUseCase
import jp.kaleidot725.adbpad.model.usecase.GetInputTextUseCase
import jp.kaleidot725.adbpad.model.usecase.StartAdbUseCase
import jp.kaleidot725.adbpad.view.resource.Menu
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainStateHolder(
    val startAdbUseCase: StartAdbUseCase = StartAdbUseCase(),
    val getAndroidDeviceListUseCase: GetAndroidDeviceListUseCase = GetAndroidDeviceListUseCase(),
    val getCommandListUseCase: GetCommandListUseCase = GetCommandListUseCase(),
    val getInputTextUseCase: GetInputTextUseCase = GetInputTextUseCase(),
    val executeCommandUseCase: ExecuteCommandUseCase = ExecuteCommandUseCase(),
    val executeInputTextUseCase: ExecuteInputTextUseCase = ExecuteInputTextUseCase()
) {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state

    fun setup() {
        coroutineScope.launch {
            val menus = Menu.values().toList()
            val commands = getCommandListUseCase()
            val inputTexts = getInputTextUseCase()

            _state.value = MainState(
                devices = emptyList(),
                selectedDevice = null,
                menus = menus,
                selectedMenu = Menu.COMMAND_MENU,
                commands = commands,
                inputTexts = inputTexts
            )

            startAdbUseCase()

            getAndroidDeviceListUseCase(coroutineScope).collect { devices ->
                _state.value = _state.value.copy(
                    devices = devices,
                    selectedDevice = when {
                        !devices.contains(_state.value.selectedDevice) -> devices.firstOrNull()
                        else -> _state.value.selectedDevice
                    }
                )
            }
        }
    }

    fun selectDevice(device: Device) {
        _state.value = _state.value.copy(selectedDevice = device)
    }

    fun selectMenu(menu: Menu) {
        _state.value = _state.value.copy(selectedMenu = menu)
    }

    fun executeCommand(command: Command) {
        coroutineScope.launch {
            val serial = _state.value.selectedDevice?.serial
            executeCommandUseCase(serial, command)
        }
    }

    fun inputText(inputText: InputText) {
        coroutineScope.launch {
            val serial = _state.value.selectedDevice?.serial
            executeInputTextUseCase(serial, inputText)
        }
    }

    fun takeScreenShot() {
    }

    fun takeThemeScreenShot() {
    }

    fun dispose() {
        coroutineScope.cancel()
    }
}
