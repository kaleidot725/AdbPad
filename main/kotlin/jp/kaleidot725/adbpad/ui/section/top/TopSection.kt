package jp.kaleidot725.adbpad.ui.section.top

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.ScreenShare
import com.composables.icons.lucide.Settings
import com.composables.icons.lucide.Settings2
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.common.resource.UserColor
import jp.kaleidot725.adbpad.ui.component.button.CommandIconButton
import jp.kaleidot725.adbpad.ui.section.top.component.DropDownDeviceMenu
import jp.kaleidot725.adbpad.ui.section.top.state.TopAction
import jp.kaleidot725.adbpad.ui.section.top.state.TopState

@Composable
fun TopSection(
    state: TopState,
    onAction: (TopAction) -> Unit,
    onMainRefresh: () -> Unit,
    onMainOpenDeviceSettings: (Device) -> Unit,
    onMainOpenSetting: () -> Unit,
    onLaunchScrcpy: () -> Unit,
) {
    TopSection(
        state = state,
        onSelectDevice = { onAction(TopAction.SelectDevice(it)) },
        onRefresh = onMainRefresh,
        onOpenDeviceSettings = onMainOpenDeviceSettings,
        onOpenSetting = onMainOpenSetting,
        onLaunchScrcpy = onLaunchScrcpy,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TopSection(
    state: TopState,
    onSelectDevice: (Device) -> Unit,
    onOpenDeviceSettings: (Device) -> Unit,
    onRefresh: () -> Unit,
    onOpenSetting: () -> Unit,
    onLaunchScrcpy: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxWidth().height(40.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DropDownDeviceMenu(
                devices = state.devices,
                selectedDevice = state.selectedDevice,
                onSelectDevice = onSelectDevice,
                onOpenDeviceSettings = onOpenDeviceSettings,
                onRefresh = onRefresh,
                modifier = Modifier.wrapContentWidth(),
            )

            if (state.selectedDevice != null) {
                CommandTooltip(
                    text = Language.tooltipScrcpy,
                ) {
                    CommandIconButton(
                        modifier = Modifier.padding(vertical = 4.dp).padding(start = 8.dp),
                        image = Lucide.ScreenShare,
                        onClick = { onLaunchScrcpy() },
                        padding = 2.dp,
                    )
                }

                CommandTooltip(
                    text = Language.tooltipSetting,
                ) {
                    CommandIconButton(
                        modifier = Modifier.padding(vertical = 4.dp).padding(start = 8.dp),
                        image = Lucide.Settings2,
                        onClick = { onOpenDeviceSettings(state.selectedDevice) },
                        padding = 2.dp,
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            CommandTooltip(
                text = Language.tooltipSetting,
            ) {
                CommandIconButton(
                    modifier = Modifier.padding(vertical = 4.dp),
                    image = Lucide.Settings,
                    onClick = onOpenSetting,
                    padding = 2.dp,
                )
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
        onSelectDevice = {},
        onRefresh = {},
        onOpenDeviceSettings = {},
        onOpenSetting = {},
        onLaunchScrcpy = {},
    )
}
