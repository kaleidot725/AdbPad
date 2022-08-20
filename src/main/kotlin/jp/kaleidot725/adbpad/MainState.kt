package jp.kaleidot725.adbpad

import com.malinskiy.adam.request.device.Device
import jp.kaleidot725.adbpad.model.Command
import jp.kaleidot725.adbpad.model.InputText
import jp.kaleidot725.adbpad.view.resource.Menu

data class MainState(
    val devices: List<Device> = emptyList(),
    val selectedDevice: Device? = null,
    val menus: List<Menu> = Menu.values().toList(),
    val selectedMenu: Menu = Menu.COMMAND_MENU,
    val commands: List<Command> = Command.values().toList(),
    val inputTexts: List<InputText> = listOf(
        InputText("ID入力", "いろはにほへと"),
        InputText("ID入力", "いろはにほへと"),
        InputText("ID入力", "いろはにほへと"),
        InputText("ID入力", "いろはにほへと"),
        InputText("ID入力", "いろはにほへと"),
        InputText("ID入力", "いろはにほへと"),
        InputText("ID入力", "いろはにほへと"),
    )
)