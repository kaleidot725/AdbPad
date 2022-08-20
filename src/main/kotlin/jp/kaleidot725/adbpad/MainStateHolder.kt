package jp.kaleidot725.adbpad

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.interactor.StartAdbInteractor
import com.malinskiy.adam.request.device.AsyncDeviceMonitorRequest
import com.malinskiy.adam.request.device.Device
import com.malinskiy.adam.request.shell.v1.ShellCommandRequest
import jp.kaleidot725.adbpad.model.Command
import jp.kaleidot725.adbpad.model.Menu
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainStateHolder {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val adb = AndroidDebugBridgeClientFactory().build()

    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state

    init {
        coroutineScope.launch {
            StartAdbInteractor().execute()

            val channel = adb.execute(
                request = AsyncDeviceMonitorRequest(),
                scope = coroutineScope
            )

            channel.receiveAsFlow().collect { devices ->
                if (devices.isNotEmpty()) {
                    val selectedDevice = _state.value.selectedDevice
                    val newSelectedDevice = when {
                        selectedDevice == null -> devices.firstOrNull()
                        !devices.contains(selectedDevice) -> devices.firstOrNull()
                        else -> selectedDevice
                    }
                    _state.value = _state.value.copy(devices = devices, selectedDevice = newSelectedDevice)
                } else {
                    _state.value = _state.value.copy(devices = devices, selectedDevice = null)
                }
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
            when (command) {
                Command.DARK_THEME_ON -> {
                    adb.execute(
                        request = ShellCommandRequest("cmd uimode night yes"),
                        serial = serial
                    )
                }

                Command.DARK_THEME_OFF -> {
                    adb.execute(
                        request = ShellCommandRequest("cmd uimode night no"),
                        serial = serial
                    )
                }

                Command.WIFI_ON -> {
                    adb.execute(
                        request = ShellCommandRequest("svc wifi enable"),
                        serial = serial
                    )
                }

                Command.WIFI_OFF -> {
                    adb.execute(
                        request = ShellCommandRequest("svc wifi disable"),
                        serial = serial
                    )
                }

                Command.DATA_ON -> {
                    adb.execute(
                        request = ShellCommandRequest("svc data enable"),
                        serial = serial
                    )
                }

                Command.DATA_OFF -> {
                    adb.execute(
                        request = ShellCommandRequest("svc data disable"),
                        serial = serial
                    )
                }

                Command.WIFI_AND_DATA_ON -> {
                    adb.execute(
                        request = ShellCommandRequest("svc wifi enable"),
                        serial = serial
                    )
                    adb.execute(
                        request = ShellCommandRequest("svc data enable"),
                        serial = serial
                    )
                }

                Command.WIFI_AND_DATA_OFF -> {
                    adb.execute(
                        request = ShellCommandRequest("svc wifi disable"),
                        serial = serial
                    )
                    adb.execute(
                        request = ShellCommandRequest("svc data disable"),
                        serial = serial
                    )
                }

                else -> {}
            }
        }
    }

    fun executeAutoFillText() {

    }

    fun takeScreenShot() {

    }

    fun takeThemeScreenShot() {

    }
}

