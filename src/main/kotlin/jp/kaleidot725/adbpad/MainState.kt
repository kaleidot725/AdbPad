package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.model.AutoFillText
import jp.kaleidot725.adbpad.model.Command
import jp.kaleidot725.adbpad.model.Menu

data class MainState(
    val devices: List<String> = listOf("端末A", "端末B", "端末C"),
    val selectedDevice: String = "",
    val menus: List<Menu> = Menu.values().toList(),
    val selectedMenu: Menu = Menu.COMMAND_MENU,
    val commands: List<Command> = Command.values().toList(),
    val autoFillTexts: List<AutoFillText> = listOf(
        AutoFillText("ID入力", "いろはにほへと"),
        AutoFillText("ID入力", "いろはにほへと"),
        AutoFillText("ID入力", "いろはにほへと"),
        AutoFillText("ID入力", "いろはにほへと"),
        AutoFillText("ID入力", "いろはにほへと"),
        AutoFillText("ID入力", "いろはにほへと"),
        AutoFillText("ID入力", "いろはにほへと"),
    )
)