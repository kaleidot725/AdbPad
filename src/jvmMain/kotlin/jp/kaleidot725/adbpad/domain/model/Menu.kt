package jp.kaleidot725.adbpad.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Send
import androidx.compose.ui.graphics.vector.ImageVector
import jp.kaleidot725.adbpad.domain.model.language.Language

interface Menu {
    val title: String
    val icon: ImageVector

    object Command : Menu {
        override val title: String
            get() = Language.MENU_COMMAND_TITLE
        override val icon: ImageVector
            get() = Icons.Default.DoubleArrow
    }

    object InputText : Menu {
        override val title: String
            get() = Language.MENU_INPUT_TEXT_TITLE
        override val icon: ImageVector
            get() = Icons.Default.Send
    }

    object Screenshot : Menu {
        override val title: String
            get() = Language.MENU_SCREENSHOT
        override val icon: ImageVector
            get() = Icons.Default.PhotoCamera
    }
}
