package jp.kaleidot725.adbpad

import com.malinskiy.adam.request.device.Device
import jp.kaleidot725.adbpad.model.data.Command
import jp.kaleidot725.adbpad.model.data.Dialog
import jp.kaleidot725.adbpad.model.data.Menu
import java.io.File

data class MainState(
    // Menus
    val menus: List<Menu> = emptyList(),
    val selectedMenu: Menu? = null,

    // Dialog
    val dialog: Dialog? = null,

    // Command
    val commands: List<Command> = emptyList(),

    // Devices
    val devices: List<Device> = emptyList(),
    val selectedDevice: Device? = null,

    // InputText
    val inputTexts: List<String> = emptyList(),
    val userInputText: String = "",

    // Screenshot
    val imageFile1: File? = null,
    val imageFile2: File? = null
) {
    val canSendUserInputText: Boolean get() = userInputText.isNotEmpty()
    val canSaveUserInputText: Boolean get() = userInputText.isNotEmpty()
}
