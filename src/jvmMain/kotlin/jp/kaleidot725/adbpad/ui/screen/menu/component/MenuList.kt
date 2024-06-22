package jp.kaleidot725.adbpad.ui.screen.menu.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.Menu
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground

@Composable
fun MenuList(
    menus: List<Menu>,
    selectedMenu: Menu?,
    onSelectMenu: (Menu) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        menus.forEach { menu ->
            val isSelected = menu == selectedMenu
            MenuItem(
                icon = menu.icon,
                iconDescription = "$menu Icon",
                text = menu.title,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .clickableBackground(isSelected = isSelected)
                        .clickable { onSelectMenu(menu) }
                        .padding(horizontal = 8.dp)
                        .padding(vertical = 8.dp),
            )
        }
    }
}

@Preview
@Composable
private fun MenuList_Preview() {
    MenuList(
        menus = listOf(Menu.Command, Menu.InputText, Menu.Screenshot),
        selectedMenu = Menu.Command,
        onSelectMenu = {},
    )
}
