package jp.kaleidot725.adbpad.view.screen.screenshot

import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.model.ScreenshotPreview
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.screenshot.GetScreenshotCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.screenshot.GetScreenshotPreviewUseCase
import jp.kaleidot725.adbpad.domain.usecase.screenshot.TakeScreenshotUseCase
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

class ScreenshotStateHolder(
    private val takeScreenshotUseCase: TakeScreenshotUseCase,
    private val getScreenshotCommandUseCase: GetScreenshotCommandUseCase,
    private val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase,
    private val getScreenshotPreviewUseCase: GetScreenshotPreviewUseCase
) : ChildStateHolder<ScreenshotState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val commands: MutableStateFlow<List<ScreenshotCommand>> = MutableStateFlow(emptyList())
    private val preview: MutableStateFlow<ScreenshotPreview> = MutableStateFlow(ScreenshotPreview(emptyList()))
    private val isCapturing: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val selectedDevice: StateFlow<Device?> = getSelectedDeviceFlowUseCase()
        .stateIn(coroutineScope, SharingStarted.WhileSubscribed(), null)

    override val state: StateFlow<ScreenshotState> = combine(
        preview,
        commands,
        selectedDevice,
        isCapturing
    ) { preview, commands, selectedDevice, isCapturing ->
        ScreenshotState(preview, commands, selectedDevice, isCapturing)
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), ScreenshotState())

    override fun setup() {
        commands.value = getScreenshotCommandUseCase()
    }

    override fun dispose() {
        coroutineScope.cancel()
    }

    fun takeScreenShot(command: ScreenshotCommand) {
        val selectedDevice = state.value.selectedDevice ?: return
        coroutineScope.launch {
            takeScreenshotUseCase(
                device = selectedDevice,
                command = command,
                onStart = {
                    commands.value = getScreenshotCommandUseCase()
                    preview.value = ScreenshotPreview(emptyList())
                    isCapturing.value = true
                },
                onFailed = {
                    commands.value = getScreenshotCommandUseCase()
                    preview.value = getScreenshotPreviewUseCase()
                    isCapturing.value = false
                },
                onComplete = {
                    commands.value = getScreenshotCommandUseCase()
                    preview.value = getScreenshotPreviewUseCase()
                    isCapturing.value = false
                }
            )
        }
    }
}
