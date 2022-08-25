package jp.kaleidot725.adbpad

import androidx.compose.ui.graphics.ImageBitmap
import com.malinskiy.adam.request.device.Device
import jp.kaleidot725.adbpad.model.data.Command
import jp.kaleidot725.adbpad.model.data.InputText
import jp.kaleidot725.adbpad.view.resource.Menu

data class MainState(
    // Menus
    val menus: List<Menu> = emptyList(),
    val selectedMenu: Menu? = null,

    // Command
    val commands: List<Command> = emptyList(),

    // Devices
    val devices: List<Device> = emptyList(),
    val selectedDevice: Device? = null,

    // InputText
    val inputTexts: List<InputText> = emptyList(),
    val inputText: InputText = InputText(""),

    // Screenshot
    val previewImageUrl1: ImageBitmap? = null,
    val previewImageUrl2: ImageBitmap? = null,
)
