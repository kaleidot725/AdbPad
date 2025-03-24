package jp.kaleidot725.adbpad.ui.section.top

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Circle
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Power
import com.composables.icons.lucide.RefreshCcw
import com.composables.icons.lucide.Square
import com.composables.icons.lucide.Triangle
import com.composables.icons.lucide.Volume1
import com.composables.icons.lucide.Volume2
import com.composables.icons.lucide.VolumeX
import jp.kaleidot725.adbpad.domain.model.command.DeviceControlCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.ui.component.button.CommandIconButton
import jp.kaleidot725.adbpad.ui.component.divider.CommandIconDivider
import jp.kaleidot725.adbpad.ui.section.top.component.DropDownDeviceMenu
import jp.kaleidot725.adbpad.ui.section.top.state.TopState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TopSection(
    state: TopState,
    onExecuteCommand: (DeviceControlCommand) -> Unit,
    onSelectDevice: (Device) -> Unit,
    onRefresh: () -> Unit,
) {
    var isPress: Boolean by remember { mutableStateOf(false) }
    val degrees: Float by animateFloatAsState(if (isPress) -90f else 0f)

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxWidth().height(40.dp),
    ) {
        Box {
            Row(Modifier.align(Alignment.CenterStart).wrapContentSize().padding(start = 4.dp)) {
                DropDownDeviceMenu(
                    devices = state.devices,
                    selectedDevice = state.selectedDevice,
                    onSelectDevice = onSelectDevice,
                    modifier = Modifier.wrapContentWidth(),
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.align(Alignment.CenterEnd).wrapContentSize().padding(4.dp),
            ) {
                CommandIconButton(
                    image = Lucide.Power,
                    onClick = { onExecuteCommand(DeviceControlCommand.Power) },
                    padding = 2.dp,
                )

                CommandIconButton(
                    image = Lucide.Volume2,
                    onClick = { onExecuteCommand(DeviceControlCommand.VolumeUp) },
                )

                CommandIconButton(
                    image = Lucide.Volume1,
                    onClick = { onExecuteCommand(DeviceControlCommand.VolumeDown) },
                )

                CommandIconButton(
                    image = Lucide.VolumeX,
                    onClick = { onExecuteCommand(DeviceControlCommand.VolumeMute) },
                )

                CommandIconDivider()

                CommandIconButton(
                    image = Lucide.Triangle,
                    degrees = -90f,
                    onClick = { onExecuteCommand(DeviceControlCommand.Back) },
                    padding = 2.dp,
                )

                CommandIconButton(
                    image = Lucide.Circle,
                    onClick = { onExecuteCommand(DeviceControlCommand.Home) },
                    padding = 2.dp,
                )

                CommandIconButton(
                    image = Lucide.Square,
                    onClick = { onExecuteCommand(DeviceControlCommand.Recents) },
                    padding = 2.dp,
                )

                CommandIconDivider()

                CommandIconButton(
                    modifier =
                        Modifier
                            .onPointerEvent(PointerEventType.Press) { isPress = true }
                            .onPointerEvent(PointerEventType.Release) { isPress = false },
                    image = Lucide.RefreshCcw,
                    degrees = degrees,
                    onClick = { onRefresh() },
                    padding = 2.dp,
                )
            }
        }
    }
}
