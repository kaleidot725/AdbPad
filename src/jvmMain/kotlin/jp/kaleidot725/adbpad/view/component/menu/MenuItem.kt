package jp.kaleidot725.adbpad.view.component.menu

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuItem(
    icon: ImageVector,
    iconDescription: String,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription,
            modifier = Modifier.align(Alignment.CenterVertically).size(20.dp),
        )

        Text(
            text = text,
            style = MaterialTheme.typography.subtitle2,
            lineHeight = 20.sp,
            modifier = Modifier.align(Alignment.CenterVertically),
        )
    }
}

@Preview
@Composable
private fun MenuItem_Preview() {
    MenuItem(
        icon = Icons.Default.Menu,
        iconDescription = "menu",
        text = "MENU_TITLE",
    )
}
