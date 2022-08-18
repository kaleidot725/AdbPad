package jp.kaleidot725.adbpad.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.filled.Input
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.ui.graphics.vector.ImageVector

enum class Menu {
    COMMAND_MENU,
    AUTOFILL_MENU,
    SCREENSHOT_MENU
}

fun Menu.toTitle(): String {
    return when (this) {
        Menu.COMMAND_MENU -> "コマンド"
        Menu.AUTOFILL_MENU -> "テキスト入力"
        Menu.SCREENSHOT_MENU -> "スクリーンショット"
    }
}

fun Menu.toIcon(): ImageVector {
    return when (this) {
        Menu.COMMAND_MENU -> Icons.Default.DoubleArrow
        Menu.AUTOFILL_MENU -> Icons.Default.Input
        Menu.SCREENSHOT_MENU -> Icons.Default.PhotoCamera
    }
}
