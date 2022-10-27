package jp.kaleidot725.adbpad.view.screen.screenshot

import com.malinskiy.adam.request.device.Device
import jp.kaleidot725.adbpad.model.data.Event
import jp.kaleidot725.adbpad.model.usecase.screenshot.TakeScreenshotUseCase
import jp.kaleidot725.adbpad.model.usecase.screenshot.TakeThemeScreenshotUseCase
import jp.kaleidot725.adbpad.view.common.ChildStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File

class ScreenshotStateHolder(
    private val takeScreenshotUseCase: TakeScreenshotUseCase = TakeScreenshotUseCase(),
    private val takeThemeScreenshotUseCase: TakeThemeScreenshotUseCase = TakeThemeScreenshotUseCase()
) : ChildStateHolder<ScreenshotState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val previewImage1: MutableStateFlow<File?> = MutableStateFlow(null)
    private val previewImage2: MutableStateFlow<File?> = MutableStateFlow(null)
    override val state: StateFlow<ScreenshotState> = combine(previewImage1, previewImage2) { image1, image2 ->
        ScreenshotState(image1, image2)
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), ScreenshotState())

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    override val event: SharedFlow<Event> = _event

    override fun setup() {}
    override fun dispose() {}

    fun takeScreenShot(device: Device) {
        coroutineScope.launch {
            val filePath = takeScreenshotUseCase(device.serial) ?: return@launch
            previewImage1.value = File(filePath)
            previewImage2.value = null
        }
    }

    fun takeThemeScreenShot(device: Device) {
        coroutineScope.launch {
            val filePathPair = takeThemeScreenshotUseCase(device.serial) ?: return@launch
            previewImage1.value = File(filePathPair.first)
            previewImage2.value = File(filePathPair.second)
        }
    }
}
