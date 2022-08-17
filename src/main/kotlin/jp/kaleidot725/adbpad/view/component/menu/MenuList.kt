package jp.kaleidot725.adbpad.view.component.menu

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.filled.Input
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.model.Menu
import jp.kaleidot725.adbpad.view.component.extension.clickableNoRipple

@Composable
fun MenuList(
    menus: List<Menu>,
    selectedMenu: Menu,
    onSelectMenu: (Menu) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        menus.forEach { menu ->
            val isSelected = menu == selectedMenu
            MenuItem(
                icon = menu.toIcon(),
                iconDescription = "$menu Icon",
                text = menu.toTitle(),
                modifier = Modifier
                    .fillMaxWidth()
                    .selectedBackground(isSelected)
                    .padding(horizontal = 8.dp)
                    .clickableNoRipple { onSelectMenu(menu) }
                    .padding(vertical = 8.dp)
            )
        }
    }
}

private fun Menu.toTitle(): String {
    return when (this) {
        Menu.COMMAND_MENU -> "コマンド"
        Menu.SCREENSHOT_MENU -> "スクリーンショット"
        Menu.AUTOFILL_MENU -> "自動入力"
    }
}

private fun Menu.toIcon(): ImageVector {
    return when (this) {
        Menu.COMMAND_MENU -> Icons.Default.DoubleArrow
        Menu.SCREENSHOT_MENU -> Icons.Default.PhotoCamera
        Menu.AUTOFILL_MENU -> Icons.Default.Input
    }
}

@Composable
private fun Modifier.selectedBackground(isSelected: Boolean): Modifier {
    return if (isSelected) {
        this.background(color = MaterialTheme.colors.primary.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp))
    } else {
        this
    }
}

@Preview
@Composable
private fun MenuList_Preview() {
    MenuList(Menu.values().toList(), Menu.COMMAND_MENU, {})
}
