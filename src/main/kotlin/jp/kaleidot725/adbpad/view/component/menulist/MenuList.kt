package jp.kaleidot725.adbpad.view.component.menulist

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.view.component.extension.clickableNoRipple

@Composable
fun MenuList(
    menus: List<String>,
    modifier: Modifier = Modifier
) {
    var selectedMenu by remember { mutableStateOf(menus.first()) }

    Column(modifier = modifier) {
        menus.forEach { menu ->
            val isSelected = menu == selectedMenu
            MenuItem(
                icon = Icons.Default.Call,
                iconDescription = "$menu Icon",
                text = menu,
                modifier = Modifier
                    .fillMaxWidth()
                    .selectedBackground(isSelected)
                    .padding(horizontal = 8.dp)
                    .clickableNoRipple { selectedMenu = menu }
                    .padding(vertical = 8.dp)
            )
        }
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
    MenuList(listOf("MENU_A", "MENU_B", "MENU_C"))
}