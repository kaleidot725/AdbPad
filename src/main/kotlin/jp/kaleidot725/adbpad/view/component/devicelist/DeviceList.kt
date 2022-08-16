package jp.kaleidot725.adbpad.view.component.menulist

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun DeviceList(
    devices: List<String>,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedDevice by remember { mutableStateOf(devices.first()) }
    var dropDownWidth by remember { mutableStateOf(0) }

    Box(modifier) {
        Box(
            modifier = Modifier
                .clickable { if (!expanded) expanded = true }
                .fillMaxWidth()
                .onSizeChanged { dropDownWidth = it.width }
                .border(
                    border = BorderStroke(2.dp, MaterialTheme.colors.primary),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(
                text = selectedDevice,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.fillMaxWidth()
            )

            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "DropDownMenuIcon",
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { dropDownWidth.toDp() })
        ) {
            devices.forEach { device ->
                DropdownMenuItem(
                    onClick = {
                        selectedDevice = device
                        expanded = false
                    }
                ) {
                    Text(
                        text = device,
                        style = MaterialTheme.typography.subtitle2,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun DeviceList_Preview() {
    DeviceList(listOf("端末A", "端末B", "端末C"))
}