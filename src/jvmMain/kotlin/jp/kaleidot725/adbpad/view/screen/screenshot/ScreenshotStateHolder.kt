package jp.kaleidot725.adbpad.view.screen.screenshot

import jp.kaleidot725.adbpad.domain.model.Device
import jp.kaleidot725.adbpad.domain.model.Screenshot
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.screenshot.TakeScreenshotUseCase
import jp.kaleidot725.adbpad.domain.usecase.screenshot.TakeThemeScreenshotUseCase
import jp.kaleidot725.adbpad.view.common.ChildStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File

class ScreenshotStateHolder(
    private val takeScreenshotUseCase: TakeScreenshotUseCase,
    private val takeThemeScreenshotUseCase: TakeThemeScreenshotUseCase,
    private val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase
) : ChildStateHolder<ScreenshotState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val previewImage1: MutableStateFlow<File?> = MutableStateFlow(null)
    private val previewImage2: MutableStateFlow<File?> = MutableStateFlow(null)
    private val selectedDevice: StateFlow<Device?> = getSelectedDeviceFlowUseCase()
        .stateIn(coroutineScope, SharingStarted.WhileSubscribed(), null)

    override val state: StateFlow<ScreenshotState> = combine(
        previewImage1,
        previewImage2,
        selectedDevice
    ) { image1, image2, selectedDevice ->
        ScreenshotState(image1, image2, selectedDevice)
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), ScreenshotState())

    override fun setup() {}
    override fun dispose() {}

    fun takeScreenShot(screenshot: Screenshot) {
        val selectedDevice = state.value.selectedDevice ?: return
        coroutineScope.launch {
            val filePath = takeScreenshotUseCase(selectedDevice.serial) ?: return@launch
            previewImage1.value = File(filePath)
            previewImage2.value = null
        }
    }
}
