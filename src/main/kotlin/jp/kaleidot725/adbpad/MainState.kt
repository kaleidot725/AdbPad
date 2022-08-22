package jp.kaleidot725.adbpad

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import com.malinskiy.adam.request.device.Device
import jp.kaleidot725.adbpad.model.data.Command
import jp.kaleidot725.adbpad.model.data.InputText
import jp.kaleidot725.adbpad.view.resource.Menu

data class MainState(
    // Devices
    val devices: List<Device> = emptyList(),
    val selectedDevice: Device? = null,

    // Menus
    val menus: List<Menu> = emptyList(),
    val selectedMenu: Menu? = null,

    // Command
    val commands: List<Command> = emptyList(),

    // InputText
    val inputText: InputText = InputText(""),
    val inputTexts: List<InputText> = emptyList(),
    
    // Screenshot
    val previewImageUrl1: ImageBitmap? = null,
    val previewImageUrl2: ImageBitmap? = null,
)
