package jp.kaleidot725.adbpad.view.component.navigationrail

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NavigationRail(
    onSelectDevice: () -> Unit,
    onOpenSetting: () -> Unit
) {
    Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        NavigationRailItem(
            icon = Icons.Default.PhoneAndroid,
            contentDescription = "device menu",
            isSelected = true,
            onClick = onSelectDevice
        )
        Spacer(Modifier.weight(1.0f))
        NavigationRailItem(
            icon = Icons.Default.Settings,
            contentDescription = "device menu",
            isSelected = false,
            onClick = onOpenSetting
        )
    }
}

@Preview
@Composable
private fun NavigationRail_Preview() {
    NavigationRail({}, {})
}