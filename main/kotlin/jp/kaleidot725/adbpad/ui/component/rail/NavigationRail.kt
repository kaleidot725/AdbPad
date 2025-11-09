package jp.kaleidot725.adbpad.ui.component.rail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Camera
import com.composables.icons.lucide.ChevronsRight
import com.composables.icons.lucide.File
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.MonitorSmartphone
import com.composables.icons.lucide.RefreshCcw
import com.composables.icons.lucide.ScreenShare
import com.composables.icons.lucide.Settings
import com.composables.icons.lucide.Settings2
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.device.DeviceState
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.common.resource.UserColor
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground
import jp.kaleidot725.adbpad.ui.screen.main.state.MainCategory
import jp.kaleidot725.adbpad.ui.section.top.component.DropDownDeviceMenu

@Composable
fun NavigationRail(
    category: MainCategory,
    onSelectCategory: (MainCategory) -> Unit,
    onOpenSetting: () -> Unit,
    devices: List<Device>,
    selectedDevice: Device?,
    onSelectDevice: (Device) -> Unit,
    onOpenDeviceSettings: (Device) -> Unit,
    onRefreshDevices: () -> Unit,
    onLaunchScrcpy: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxHeight().padding(8.dp).width(260.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DropDownDeviceMenu(
                devices = devices,
                selectedDevice = selectedDevice,
                onSelectDevice = onSelectDevice,
                modifier = Modifier.weight(1f),
            )

            NavigationRailIconButton(
                tooltip = Language.tooltipScrcpy,
                icon = Lucide.ScreenShare,
                onClick = onLaunchScrcpy,
            )

            NavigationRailIconButton(
                tooltip = Language.tooltipSetting,
                icon = Lucide.Settings2,
                onClick = {
                    selectedDevice?.let { onOpenDeviceSettings(it) }
                },
                enabled = selectedDevice != null,
            )

            NavigationRailIconButton(
                tooltip = Language.tooltipRefresh,
                icon = Lucide.RefreshCcw,
                onClick = onRefreshDevices,
                rotateOnPress = true,
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        NavigationRailItem(
            label = Language.tooltipCommand,
            icon = Lucide.ChevronsRight,
            contentDescription = "device menu",
            isSelected = category == MainCategory.Command,
            onClick = { onSelectCategory(MainCategory.Command) },
        )

        NavigationRailItem(
            label = Language.tooltipText,
            icon = Lucide.File,
            contentDescription = "text menu",
            isSelected = category == MainCategory.Text,
            onClick = { onSelectCategory(MainCategory.Text) },
        )

        NavigationRailItem(
            label = Language.tooltipScreenshot,
            icon = Lucide.Camera,
            contentDescription = "screenshot menu",
            isSelected = category == MainCategory.Screenshot,
            onClick = { onSelectCategory(MainCategory.Screenshot) },
        )

        NavigationRailItem(
            label = Language.tooltipNewDisplay,
            icon = Lucide.MonitorSmartphone,
            contentDescription = "virtual display menu",
            isSelected = category == MainCategory.ScrcpyNewDisplay,
            onClick = { onSelectCategory(MainCategory.ScrcpyNewDisplay) },
        )

        Spacer(modifier = Modifier.weight(1f, fill = true))

        NavigationRailItem(
            label = Language.tooltipSetting,
            icon = Lucide.Settings,
            contentDescription = "app setting",
            isSelected = false,
            onClick = onOpenSetting,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
private fun NavigationRailIconButton(
    tooltip: String,
    icon: ImageVector,
    onClick: () -> Unit,
    enabled: Boolean = true,
    rotateOnPress: Boolean = false,
) {
    val backgroundShape = remember { RoundedCornerShape(8.dp) }
    var isPressed by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(if (rotateOnPress && isPressed) -90f else 0f)
    TooltipArea(
        delayMillis = 300,
        tooltip = {
            Card(
                modifier = Modifier.border(1.dp, UserColor.getSplitterColor()),
            ) {
                Text(
                    text = tooltip,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                )
            }
        },
        tooltipPlacement =
            TooltipPlacement.ComponentRect(
                anchor = Alignment.TopCenter,
                alignment = Alignment.BottomCenter,
                offset = DpOffset(0.dp, 30.dp),
            ),
    ) {
        Box(
            modifier =
                Modifier
                    .clip(backgroundShape)
                    .clickableBackground(isSelected = false, shape = backgroundShape)
                    .onPointerEvent(PointerEventType.Press) { if (rotateOnPress) isPressed = true }
                    .onPointerEvent(PointerEventType.Release) { if (rotateOnPress) isPressed = false }
                    .clickable(enabled = enabled) {
                        if (rotateOnPress) isPressed = false
                        onClick()
                    }.padding(8.dp),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = tooltip,
                modifier = Modifier.size(16.dp).graphicsLayer(rotationZ = rotation),
                tint = if (enabled) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
            )
        }
    }
}

@Preview
@Composable
private fun NavigationRail_Preview() {
    val sample = Device("id", "Pixel", DeviceState.DEVICE)
    NavigationRail(
        category = MainCategory.Text,
        onSelectCategory = {},
        onOpenSetting = {},
        devices = listOf(sample),
        selectedDevice = sample,
        onSelectDevice = {},
        onOpenDeviceSettings = {},
        onRefreshDevices = {},
        onLaunchScrcpy = {},
    )
}
