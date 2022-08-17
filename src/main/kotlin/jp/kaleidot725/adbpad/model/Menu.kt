package jp.kaleidot725.adbpad.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.filled.Input
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.ui.graphics.vector.ImageVector

enum class Menu {
    COMMAND_MENU,
    SCREENSHOT_MENU,
    AUTOFILL_MENU
}

fun Menu.toTitle(): String {
    return when (this) {
        Menu.COMMAND_MENU -> "コマンド"
        Menu.SCREENSHOT_MENU -> "スクリーンショット"
        Menu.AUTOFILL_MENU -> "自動入力"
    }
}

fun Menu.toIcon(): ImageVector {
    return when (this) {
        Menu.COMMAND_MENU -> Icons.Default.DoubleArrow
        Menu.SCREENSHOT_MENU -> Icons.Default.PhotoCamera
        Menu.AUTOFILL_MENU -> Icons.Default.Input
    }
}
