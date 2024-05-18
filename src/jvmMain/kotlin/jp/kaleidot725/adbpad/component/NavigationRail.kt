package jp.kaleidot725.adbpad.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Settings
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
            icon = Icons.Default.PhoneAndroid,
            contentDescription = "device menu",
            isSelected = category == MainCategory.Device,
            onClick = { onSelectCategory(MainCategory.Device) },
        )

        NavigationRailItem(
            icon = Icons.Default.Android,
            contentDescription = "version menu",
            isSelected = category == MainCategory.Version,
            onClick = { onSelectCategory(MainCategory.Version) },
        )

        Spacer(Modifier.weight(1.0f))
        NavigationRailItem(
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
    NavigationRail(MainCategory.Version, {}, {})
}
