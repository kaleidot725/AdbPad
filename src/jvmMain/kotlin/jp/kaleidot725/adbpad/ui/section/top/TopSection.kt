package jp.kaleidot725.adbpad.ui.section.top

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Circle
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Power
import com.composables.icons.lucide.RefreshCcw
import com.composables.icons.lucide.ScreenShare
import com.composables.icons.lucide.Settings2
import com.composables.icons.lucide.Square
import com.composables.icons.lucide.Triangle
import com.composables.icons.lucide.Volume1
import com.composables.icons.lucide.Volume2
import com.composables.icons.lucide.VolumeX
import jp.kaleidot725.adbpad.domain.model.command.DeviceControlCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.common.resource.UserColor
import jp.kaleidot725.adbpad.ui.component.button.CommandIconButton
import jp.kaleidot725.adbpad.ui.component.divider.CommandIconDivider
import jp.kaleidot725.adbpad.ui.section.top.component.DropDownDeviceMenu
import jp.kaleidot725.adbpad.ui.section.top.state.TopAction
import jp.kaleidot725.adbpad.ui.section.top.state.TopState

@Composable
fun TopSection(
    state: TopState,
    onAction: (TopAction) -> Unit,
    onMainRefresh: () -> Unit,
    onMainOpenDeviceSettings: (Device) -> Unit,
) {
    TopSection(
        state = state,
        onExecuteCommand = { onAction(TopAction.ExecuteCommand(it)) },
        onSelectDevice = { onAction(TopAction.SelectDevice(it)) },
        onLaunchScrcpy = { onAction(TopAction.LaunchScrcpy) },
        onRefresh = onMainRefresh,
        onOpenDeviceSettings = onMainOpenDeviceSettings,
    )
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
private fun TopSection(
    state: TopState,
    onExecuteCommand: (DeviceControlCommand) -> Unit,
    onSelectDevice: (Device) -> Unit,
    onLaunchScrcpy: () -> Unit,
    onOpenDeviceSettings: (Device) -> Unit,
    onRefresh: () -> Unit,
) {
    var isPress: Boolean by remember { mutableStateOf(false) }
    val degrees: Float by animateFloatAsState(if (isPress) -90f else 0f)

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxWidth().height(40.dp),
    ) {
        Box {
            Row(Modifier.align(Alignment.CenterStart).wrapContentSize().padding(start = 12.dp)) {
                DropDownDeviceMenu(
                    devices = state.devices,
                    selectedDevice = state.selectedDevice,
                    onSelectDevice = onSelectDevice,
                    onOpenDeviceSettings = onOpenDeviceSettings,
                    modifier = Modifier.wrapContentWidth().align(Alignment.CenterVertically),
                )

                if (state.selectedDevice != null) {
                    CommandTooltip(
                        text = Language.tooltipSetting,
                    ) {
                        CommandIconButton(
                            modifier = Modifier.padding(vertical = 4.dp),
                            image = Lucide.Settings2,
                            onClick = { onOpenDeviceSettings(state.selectedDevice) },
                            padding = 2.dp,
                        )
                    }
                }

                CommandTooltip(
                    text = Language.tooltipRefresh,
                ) {
                    CommandIconButton(
                        modifier =
                            Modifier
                                .padding(vertical = 4.dp)
                                .onPointerEvent(PointerEventType.Press) { isPress = true }
                                .onPointerEvent(PointerEventType.Release) { isPress = false },
                        image = Lucide.RefreshCcw,
                        degrees = degrees,
                        onClick = { onRefresh() },
                        padding = 2.dp,
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.align(Alignment.CenterEnd).wrapContentSize().padding(4.dp),
            ) {
                CommandTooltip(
                    text = Language.tooltipPower,
                ) {
                    CommandIconButton(
                        image = Lucide.Power,
                        onClick = { onExecuteCommand(DeviceControlCommand.Power) },
                        padding = 2.dp,
                    )
                }

                CommandTooltip(
                    text = Language.tooltipVolumeUp,
                ) {
                    CommandIconButton(
                        image = Lucide.Volume2,
                        onClick = { onExecuteCommand(DeviceControlCommand.VolumeUp) },
                    )
                }

                CommandTooltip(
                    text = Language.tooltipVolumeDown,
                ) {
                    CommandIconButton(
                        image = Lucide.Volume1,
                        onClick = { onExecuteCommand(DeviceControlCommand.VolumeDown) },
                    )
                }

                CommandTooltip(
                    text = Language.tooltipVolumeMute,
                ) {
                    CommandIconButton(
                        image = Lucide.VolumeX,
                        onClick = { onExecuteCommand(DeviceControlCommand.VolumeMute) },
                    )
                }

                CommandIconDivider()

                CommandTooltip(
                    text = Language.tooltipBack,
                ) {
                    CommandIconButton(
                        image = Lucide.Triangle,
                        degrees = -90f,
                        onClick = { onExecuteCommand(DeviceControlCommand.Back) },
                        padding = 2.dp,
                    )
                }

                CommandTooltip(
                    text = Language.tooltipHome,
                ) {
                    CommandIconButton(
                        image = Lucide.Circle,
                        onClick = { onExecuteCommand(DeviceControlCommand.Home) },
                        padding = 2.dp,
                    )
                }

                CommandTooltip(
                    text = Language.tooltipRecents,
                ) {
                    CommandIconButton(
                        image = Lucide.Square,
                        onClick = { onExecuteCommand(DeviceControlCommand.Recents) },
                        padding = 2.dp,
                    )
                }

                CommandIconDivider()

                CommandTooltip(
                    text = Language.tooltipScrcpy,
                ) {
                    CommandIconButton(
                        image = Lucide.ScreenShare,
                        onClick = { onLaunchScrcpy() },
                        padding = 2.dp,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CommandTooltip(
    text: String,
    tooltipPlacement: TooltipPlacement =
        TooltipPlacement.ComponentRect(
            anchor = Alignment.TopCenter,
            alignment = Alignment.BottomCenter,
            offset = DpOffset(0.dp, 30.dp),
        ),
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    TooltipArea(
        delayMillis = 300,
        tooltip = {
            Card(
                modifier = Modifier.border(1.dp, UserColor.getSplitterColor()),
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                )
            }
        },
        tooltipPlacement = tooltipPlacement,
        modifier = modifier,
        content = content,
    )
}

@Preview
@Composable
private fun Preview() {
    TopSection(
        state = TopState(),
        onExecuteCommand = {},
        onSelectDevice = {},
        onLaunchScrcpy = {},
        onRefresh = {},
        onOpenDeviceSettings = {},
    )
}
