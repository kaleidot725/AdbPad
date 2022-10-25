package jp.kaleidot725.adbpad

import com.malinskiy.adam.request.device.Device
import jp.kaleidot725.adbpad.model.data.Dialog
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


    val takeScreenshotUseCase: TakeScreenshotUseCase = TakeScreenshotUseCase(),
    val takeThemeScreenshotUseCase: TakeThemeScreenshotUseCase = TakeThemeScreenshotUseCase()
) : StateHolder<MainState> {
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + Dispatchers.IO)
    private val previewImage1: MutableStateFlow<File?> = MutableStateFlow(null)
    private val previewImage2: MutableStateFlow<File?> = MutableStateFlow(null)
    private val dialog: MutableStateFlow<Dialog?> = MutableStateFlow(null)

    override val state: StateFlow<MainState> = combine(
        previewImage1,
        previewImage2,
        dialog
    ) { previewImage1, previewImage2, dialog ->
        MainState(
            imageFile1 = previewImage1,
            imageFile2 = previewImage2,
            dialog = dialog
        )
    }.stateIn(coroutineScope, SharingStarted.WhileSubscribed(), MainState())

    override fun setup() {}

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
