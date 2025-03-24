package jp.kaleidot725.adbpad.ui.component.rail

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Textsms
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.MainCategory

@Composable
fun NavigationRail(
    category: MainCategory,
    onSelectCategory: (MainCategory) -> Unit,
    onOpenSetting: () -> Unit,
) {
    Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        NavigationRailItem(
            label = "Command",
            icon = Icons.Default.DoubleArrow,
            contentDescription = "device menu",
            isSelected = category == MainCategory.Command,
            onClick = { onSelectCategory(MainCategory.Command) },
        )

        NavigationRailItem(
            label = "Text",
            icon = Icons.Default.Textsms,
            contentDescription = "text menu",
            isSelected = category == MainCategory.Text,
            onClick = { onSelectCategory(MainCategory.Text) },
        )

        NavigationRailItem(
            label = "Screenshot",
            icon = Icons.Default.PhotoCamera,
            contentDescription = "screenshot menu",
            isSelected = category == MainCategory.Screenshot,
            onClick = { onSelectCategory(MainCategory.Screenshot) },
        )

        Spacer(Modifier.weight(1.0f))

        NavigationRailItem(
            label = "Setting",
            icon = Icons.Default.Settings,
            contentDescription = "device menu",
            isSelected = false,
            onClick = onOpenSetting,
        )
    }
}

@Preview
@Composable
private fun NavigationRail_Preview() {
    NavigationRail(MainCategory.Text, {}, {})
}
