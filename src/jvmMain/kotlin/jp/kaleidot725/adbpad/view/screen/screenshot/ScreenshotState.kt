package jp.kaleidot725.adbpad.view.screen.screenshot

import jp.kaleidot725.adbpad.domain.model.Device
import java.io.File

data class ScreenshotState(
    val imageFile1: File? = null,
    val imageFile2: File? = null,
    val selectedDevice: Device? = null
)
