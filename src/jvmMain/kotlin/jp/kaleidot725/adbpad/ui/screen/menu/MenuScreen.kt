package jp.kaleidot725.adbpad.ui.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.Menu
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.screen.menu.component.DropDownDeviceMenu
import jp.kaleidot725.adbpad.ui.screen.menu.component.MenuList

@Composable
fun MenuScreen(
    menus: List<Menu>,
    selectedMenu: Menu?,
    onSelectMenu: (Menu) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier,
    ) {
        Text(
            text = Language.tool,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(start = 8.dp)
        )

        MenuList(
            menus = menus,
            selectedMenu = selectedMenu,
            onSelectMenu = onSelectMenu,
            modifier = Modifier.fillMaxWidth().weight(weight = 0.9f, fill = true),
        )
    }
}

@Preview
@Composable
private fun MenuScreen_Preview() {
    MenuScreen(
        menus = listOf(Menu.Command, Menu.InputText, Menu.Screenshot),
        selectedMenu = Menu.Command,
        onSelectMenu = {},
    )
}
