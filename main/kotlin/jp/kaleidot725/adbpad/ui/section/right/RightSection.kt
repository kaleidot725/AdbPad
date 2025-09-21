package jp.kaleidot725.adbpad.ui.section.right

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeDown
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Circle
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Power
import com.composables.icons.lucide.ScreenShare
import com.composables.icons.lucide.Speaker
import com.composables.icons.lucide.Square
import com.composables.icons.lucide.Triangle
import com.composables.icons.lucide.Volume
import com.composables.icons.lucide.Volume1
import com.composables.icons.lucide.Volume2
import com.composables.icons.lucide.VolumeX
import jp.kaleidot725.adbpad.domain.model.command.DeviceControlCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.device.DeviceState
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.common.resource.UserColor
import jp.kaleidot725.adbpad.ui.component.button.CommandIconButton
import jp.kaleidot725.adbpad.ui.component.divider.CommandIconDivider
import jp.kaleidot725.adbpad.ui.section.right.state.RightAction
import jp.kaleidot725.adbpad.ui.section.right.state.RightState

@Composable
fun RightSection(
    state: RightState,
    onAction: (RightAction) -> Unit,
) {
    RightSection(
        selectedDevice = state.selectedDevice,
        onExecuteCommand = { onAction(RightAction.ExecuteCommand(it)) },
        onLaunchScrcpy = { onAction(RightAction.LaunchScrcpy) },
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RightSection(
    selectedDevice: Device?,
    onExecuteCommand: (DeviceControlCommand) -> Unit,
    onLaunchScrcpy: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxHeight().width(50.dp),
    ) {
        if (selectedDevice != null) {
            Column(
                modifier = Modifier.fillMaxHeight().padding(vertical = 8.dp, horizontal = 2.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CommandTooltip(
                    text = Language.tooltipPower,
                ) {
                    CommandIconButton(
                        modifier = Modifier.wrapContentSize(),
                        image = Lucide.Power,
                        onClick = { onExecuteCommand(DeviceControlCommand.Power) },
                        padding = 2.dp,
                    )
                }

                CommandTooltip(
                    text = Language.tooltipVolumeUp,
                ) {
                    CommandIconButton(
                        modifier = Modifier.wrapContentSize(),
                        image = Icons.AutoMirrored.Filled.VolumeUp,
                        onClick = { onExecuteCommand(DeviceControlCommand.VolumeUp) },
                        padding = 0.dp,
                    )
                }

                CommandTooltip(
                    text = Language.tooltipVolumeDown,
                ) {
                    CommandIconButton(
                        modifier = Modifier.wrapContentSize(),
                        image = Icons.AutoMirrored.Filled.VolumeDown,
                        onClick = { onExecuteCommand(DeviceControlCommand.VolumeDown) },
                        padding = 0.dp,
                    )
                }

                CommandTooltip(
                    text = Language.tooltipVolumeMute,
                ) {
                    CommandIconButton(
                        modifier = Modifier.wrapContentSize(),
                        image = Icons.AutoMirrored.Filled.VolumeOff,
                        onClick = { onExecuteCommand(DeviceControlCommand.VolumeMute) },
                        padding = 0.dp,
                    )
                }

                CommandTooltip(
                    text = Language.tooltipBack,
                ) {
                    CommandIconButton(
                        modifier = Modifier.wrapContentSize(),
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
                        modifier = Modifier.wrapContentSize(),
                        image = Lucide.Circle,
                        onClick = { onExecuteCommand(DeviceControlCommand.Home) },
                        padding = 2.dp,
                    )
                }

                CommandTooltip(
                    text = Language.tooltipRecents,
                ) {
                    CommandIconButton(
                        modifier = Modifier.wrapContentSize(),
                        image = Lucide.Square,
                        onClick = { onExecuteCommand(DeviceControlCommand.Recents) },
                        padding = 2.dp,
                    )
                }

                CommandTooltip(
                    text = Language.tooltipScrcpy,
                ) {
                    CommandIconButton(
                        modifier = Modifier.wrapContentSize(),
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
    RightSection(
        state =
            RightState(
                selectedDevice = Device(serial = "test", name = "Test Device", state = DeviceState.DEVICE),
            ),
        onAction = {},
    )
}
