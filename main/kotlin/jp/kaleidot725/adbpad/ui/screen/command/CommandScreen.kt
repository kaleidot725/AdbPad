package jp.kaleidot725.adbpad.ui.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandCategory
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandGroup
import jp.kaleidot725.adbpad.ui.common.resource.UserColor
import jp.kaleidot725.adbpad.ui.screen.command.component.CommandLayoutToggle
import jp.kaleidot725.adbpad.ui.screen.command.component.CommandList
import jp.kaleidot725.adbpad.ui.screen.command.component.CommandTab
import jp.kaleidot725.adbpad.ui.screen.command.model.CommandLayoutMode
import jp.kaleidot725.adbpad.ui.screen.command.state.CommandAction
import jp.kaleidot725.adbpad.ui.screen.command.state.CommandState

@Composable
fun CommandScreen(
    state: CommandState,
    onAction: (CommandAction) -> Unit,
) {
    CommandScreen(
        commands = state.commands,
        filtered = state.filtered,
        layoutMode = state.layoutMode,
        onClickFilter = { onAction(CommandAction.ClickCategoryTab(it)) },
        onToggleLayout = { onAction(CommandAction.ToggleLayoutMode) },
        canExecute = state.canExecuteCommand,
        onExecute = { command -> onAction(CommandAction.ExecuteCommand(command)) },
    )
}

@Composable
private fun CommandScreen(
    commands: NormalCommandGroup,
    filtered: NormalCommandCategory,
    layoutMode: CommandLayoutMode,
    onClickFilter: (NormalCommandCategory) -> Unit,
    onToggleLayout: () -> Unit,
    canExecute: Boolean,
    onExecute: (NormalCommand) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.wrapContentHeight().padding(horizontal = 8.dp).padding(vertical = 4.dp),
        ) {
            CommandTab(
                filtered = filtered,
                onClick = onClickFilter,
                modifier = Modifier.weight(1f),
            )

            CommandLayoutToggle(
                layoutMode = layoutMode,
                onToggle = onToggleLayout,
            )
        }

        Divider(color = UserColor.getSplitterColor())

        CommandList(
            commands =
                when (filtered) {
                    NormalCommandCategory.UI -> commands.ui
                    NormalCommandCategory.COM -> commands.communication
                    NormalCommandCategory.ALL -> commands.all
                },
            canExecute = canExecute,
            onExecute = onExecute,
            layoutMode = layoutMode,
            modifier = Modifier.fillMaxSize().padding(16.dp),
        )
    }
}

@Preview
@Composable
private fun CommandScreen_Card_Preview() {
    CommandScreen(
        commands =
            NormalCommandGroup(
                listOf(NormalCommand.DarkThemeOn(), NormalCommand.DarkThemeOff(), NormalCommand.WifiOn()),
                listOf(NormalCommand.DarkThemeOn(), NormalCommand.DarkThemeOff()),
                listOf(NormalCommand.WifiOn()),
            ),
        filtered = NormalCommandCategory.ALL,
        layoutMode = CommandLayoutMode.CARD,
        onClickFilter = {},
        onToggleLayout = {},
        canExecute = true,
        onExecute = {},
    )
}

@Preview
@Composable
private fun CommandScreen_List_Preview() {
    CommandScreen(
        commands =
            NormalCommandGroup(
                listOf(NormalCommand.DarkThemeOn(), NormalCommand.DarkThemeOff(), NormalCommand.WifiOn()),
                listOf(NormalCommand.DarkThemeOn(), NormalCommand.DarkThemeOff()),
                listOf(NormalCommand.WifiOn()),
            ),
        filtered = NormalCommandCategory.ALL,
        layoutMode = CommandLayoutMode.LIST,
        onClickFilter = {},
        onToggleLayout = {},
        canExecute = true,
        onExecute = {},
    )
}
