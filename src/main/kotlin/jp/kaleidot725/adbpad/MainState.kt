package jp.kaleidot725.adbpad

import androidx.compose.ui.graphics.ImageBitmap
import com.malinskiy.adam.request.device.Device
import jp.kaleidot725.adbpad.model.data.Command
import jp.kaleidot725.adbpad.model.data.Menu

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
    val inputTexts: List<String> = emptyList(),
    val userInputText: String = "",

    // Screenshot
    val previewImageUrl1: ImageBitmap? = null,
    val previewImageUrl2: ImageBitmap? = null,
) {
    val canSendUserInputText: Boolean get() = userInputText.isNotEmpty()
    val canSaveUserInputText: Boolean get() = userInputText.isNotEmpty()
}
