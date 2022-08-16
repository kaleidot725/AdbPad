package jp.kaleidot725.adbpad.view.component.menulist

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.view.component.extension.clickableNoRipple

@Composable
fun MenuList(
    menus: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        menus.forEach { menu ->
            MenuItem(
                icon = Icons.Default.Call,
                iconDescription = "$menu Icon",
                text = menu,
                modifier = Modifier.fillMaxWidth().clickableNoRipple { }
            )
        }
    }
}

@Preview
@Composable
private fun MenuList_Preview() {
    MenuList(listOf("MENU_A", "MENU_B", "MENU_C"))
}