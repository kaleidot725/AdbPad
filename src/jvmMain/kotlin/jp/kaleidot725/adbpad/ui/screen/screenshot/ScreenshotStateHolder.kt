package jp.kaleidot725.adbpad.ui.screen.screenshot

import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.screenshot.Screenshot
import jp.kaleidot725.adbpad.domain.repository.ScreenshotCommandRepository
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.screenshot.GetScreenshotCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.screenshot.TakeScreenshotUseCase
import jp.kaleidot725.adbpad.domain.utils.ClipBoardUtils
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

class ScreenshotStateHolder(
    private val takeScreenshotUseCase: TakeScreenshotUseCase,
    private val getScreenshotCommandUseCase: GetScreenshotCommandUseCase,
    private val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase,
    private val screenshotCommandRepository: ScreenshotCommandRepository,
) : ChildStateHolder<ScreenshotState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val commands: MutableStateFlow<List<ScreenshotCommand>> = MutableStateFlow(emptyList())
    private val preview: MutableStateFlow<Screenshot> = MutableStateFlow(Screenshot(null))
    private val previews: MutableStateFlow<List<Screenshot>> = MutableStateFlow(emptyList())
    private val isCapturing: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val selectedDevice: StateFlow<Device?> =
        getSelectedDeviceFlowUseCase()
            .stateIn(coroutineScope, SharingStarted.WhileSubscribed(), null)

    override val state: StateFlow<ScreenshotState> =
        combine(
            preview,
            previews,
            commands,
            selectedDevice,
            isCapturing,
        ) { preview, previews, commands, selectedDevice, isCapturing ->
            ScreenshotState(preview, previews,commands, selectedDevice, isCapturing)
        }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), ScreenshotState())

    override fun setup() {
        coroutineScope.launch {
            commands.value = getScreenshotCommandUseCase()
            previews.value = screenshotCommandRepository.getScreenshots()
        }
    }

    override fun refresh() {
        coroutineScope.launch {
            commands.value = getScreenshotCommandUseCase()
            previews.value = screenshotCommandRepository.getScreenshots()
        }
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
                    preview.value = Screenshot.EMPTY
                    isCapturing.value = true
                },
                onFailed = {
                    commands.value = getScreenshotCommandUseCase()
                    preview.value = Screenshot.EMPTY
                    isCapturing.value = false
                },
                onComplete = {
                    commands.value = getScreenshotCommandUseCase()
                    previews.value = screenshotCommandRepository.getScreenshots()
                    preview.value = it
                    isCapturing.value = false
                },
            )
        }
    }

    fun copyScreenShotToClipboard() {
        coroutineScope.launch {
            val file = preview.value.file ?: return@launch
            ClipBoardUtils.copyFile(file)
        }
    }

    fun deleteScreenShotToClipboard() {
        coroutineScope.launch {
            screenshotCommandRepository.delete(preview.value)
            preview.value = Screenshot(null)
        }
    }
}
