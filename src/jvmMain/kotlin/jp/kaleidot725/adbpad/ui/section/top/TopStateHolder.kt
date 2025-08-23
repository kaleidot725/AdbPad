package jp.kaleidot725.adbpad.ui.section.top

import jp.kaleidot725.adbpad.core.mvi.MVIBase
import jp.kaleidot725.adbpad.domain.model.command.DeviceControlCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.usecase.command.ExecuteDeviceControlCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.SelectDeviceUseCase
import jp.kaleidot725.adbpad.domain.usecase.device.UpdateDevicesUseCase
import jp.kaleidot725.adbpad.ui.section.top.state.TopAction
import jp.kaleidot725.adbpad.ui.section.top.state.TopSideEffect
import jp.kaleidot725.adbpad.ui.section.top.state.TopState
import jp.kaleidot725.scrcpykt.ScrcpyClient
import jp.kaleidot725.scrcpykt.ScrcpyResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class TopStateHolder(
    private val updateDevicesUseCase: UpdateDevicesUseCase,
    private val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase,
    private val selectDeviceUseCase: SelectDeviceUseCase,
    private val executeDeviceControlCommandUseCase: ExecuteDeviceControlCommandUseCase,
) : MVIBase<TopState, TopAction, TopSideEffect>(TopState()) {
    private var deviceJob: Job? = null
    private var selectedDeviceJob: Job? = null

    override fun onSetup() {
        collectDevices()
    }

    override fun onRefresh() {
        collectDevices()
    }

    override fun onAction(uiAction: TopAction) {
        coroutineScope.launch {
            when (uiAction) {
                is TopAction.ExecuteCommand -> executeCommand(uiAction.command)
                is TopAction.SelectDevice -> selectDevice(uiAction.device)
                is TopAction.LaunchScrcpy -> launchScrcpy()
            }
        }
    }

    private suspend fun selectDevice(device: Device) {
        selectDeviceUseCase(device)
    }

    private suspend fun executeCommand(command: DeviceControlCommand) {
        val device = currentState.selectedDevice ?: return
        executeDeviceControlCommandUseCase(
            device = device,
            command = command,
        )
    }

    private fun launchScrcpy() {
        val device = currentState.selectedDevice ?: return

        coroutineScope.launch {
            try {
                val client = ScrcpyClient.create()
                val result =
                    client.mirror {
                        connection {
                            serial(device.serial)
                        }
                        display {
                            windowTitle("AdbPad - ${device.name}")
                        }
                    }

                when (result) {
                    is ScrcpyResult.Success -> {
                        println("Scrcpy mirroring started for device: ${device.name}")
                    }
                    is ScrcpyResult.Error -> {
                        println("Failed to launch Scrcpy: ${result.message}")
                    }
                }
            } catch (e: Exception) {
                println("Failed to launch Scrcpy: ${e.message}")
            }
        }
    }

    private fun collectDevices() {
        deviceJob?.cancel()
        deviceJob =
            coroutineScope.launch {
                while (isActive) {
                    val devices = updateDevicesUseCase()
                    update { this.copy(devices = devices) }
                    delay(1000)
                }
            }

        selectedDeviceJob?.cancel()
        selectedDeviceJob =
            coroutineScope.launch {
                getSelectedDeviceFlowUseCase().collect {
                    update { this.copy(selectedDevice = it) }
                }
            }
    }
}
