package jp.kaleidot725.adbpad

import com.malinskiy.adam.request.device.Device
import jp.kaleidot725.adbpad.model.data.Command
import jp.kaleidot725.adbpad.model.data.InputText
import jp.kaleidot725.adbpad.view.resource.Menu

data class MainState(
    val devices: List<Device> = emptyList(),
    val selectedDevice: Device? = null,
    val menus: List<Menu> = emptyList(),
    val selectedMenu: Menu? = null,
    val commands: List<Command> = emptyList(),
    val inputText: InputText = InputText(""),
    val inputTexts: List<InputText> = emptyList(),
)
