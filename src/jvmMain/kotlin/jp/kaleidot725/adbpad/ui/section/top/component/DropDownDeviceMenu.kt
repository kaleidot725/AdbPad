package jp.kaleidot725.adbpad.ui.section.top.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.RefreshCcw
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.device.DeviceState
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.common.resource.UserColor
import jp.kaleidot725.adbpad.ui.component.button.CommandIconButton

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun DropDownDeviceMenu(
    devices: List<Device>,
    selectedDevice: Device?,
    onSelectDevice: (Device) -> Unit,
    onOpenDeviceSettings: (Device) -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    var dropDownWidth by remember { mutableStateOf(0) }

    Box(modifier) {
        DeviceSelector(
            selectedDevice = selectedDevice,
            onClick = { if (!expanded && devices.isNotEmpty()) expanded = true },
            modifier =
                Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(4.dp))
                    .onSizeChanged { dropDownWidth = it.width },
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier =
                Modifier
                    .widthIn(min = with(LocalDensity.current) { dropDownWidth.toDp() })
                    .background(
                        color = UserColor.getDropdownBackgroundColor(),
                        shape = RoundedCornerShape(4.dp),
                    ).border(
                        width = 1.dp,
                        color = UserColor.getSplitterColor(),
                        shape = RoundedCornerShape(4.dp),
                    ),
        ) {
            // Header with refresh button
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Devices",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
                
                RefreshButton(onRefresh = onRefresh)
            }
            
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 8.dp),
                color = UserColor.getSplitterColor()
            )
            
            devices.forEach { device ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = device.displayName,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(horizontal = 4.dp),
                        )
                    },
                    onClick = {
                        onSelectDevice(device)
                        expanded = false
                    },
                )
            }
        }
    }
}

@Composable
private fun RefreshButton(
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isPress by remember { mutableStateOf(false) }
    val degrees by animateFloatAsState(if (isPress) -90f else 0f)

    TooltipArea(
        delayMillis = 300,
        tooltip = {
            Card(
                modifier = Modifier.border(1.dp, UserColor.getSplitterColor()),
            ) {
                Text(
                    text = Language.tooltipRefresh,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                )
            }
        },
        tooltipPlacement = TooltipPlacement.ComponentRect(
            anchor = Alignment.TopCenter,
            alignment = Alignment.BottomCenter,
            offset = DpOffset(0.dp, 30.dp),
        ),
        modifier = modifier,
    ) {
        CommandIconButton(
            modifier = Modifier
                .onPointerEvent(PointerEventType.Press) { isPress = true }
                .onPointerEvent(PointerEventType.Release) { isPress = false },
            image = Lucide.RefreshCcw,
            degrees = degrees,
            onClick = { onRefresh() },
            padding = 2.dp,
        )
    }
}

@Preview
@Composable
private fun DeviceList_Preview() {
    val sample = Device("DEVICE", "NAME", DeviceState.DEVICE)
    DropDownDeviceMenu(
        devices = listOf(sample),
        selectedDevice = sample,
        onSelectDevice = {},
        onOpenDeviceSettings = {},
        onRefresh = {},
    )
}
