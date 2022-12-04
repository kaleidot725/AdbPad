package jp.kaleidot725.adbpad.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Send
import androidx.compose.ui.graphics.vector.ImageVector
import jp.kaleidot725.adbpad.domain.model.language.Language

sealed class Menu(val title: String, val icon: ImageVector) {
    object Command : Menu(Language.MENU_COMMAND_TITLE, Icons.Default.DoubleArrow)
    object InputText : Menu(Language.MENU_INPUT_TEXT_TITLE, Icons.Default.Send)
    object Screenshot : Menu(Language.MENU_SCREENSHOT, Icons.Default.PhotoCamera)
}
