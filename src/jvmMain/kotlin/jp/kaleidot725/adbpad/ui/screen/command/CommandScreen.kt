package jp.kaleidot725.adbpad.ui.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandCategory
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandGroup
import jp.kaleidot725.adbpad.ui.common.resource.UserColor
import jp.kaleidot725.adbpad.ui.screen.command.component.CommandList
import jp.kaleidot725.adbpad.ui.screen.command.component.CommandTab
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
        onClickFilter = { onAction(CommandAction.ClickCategoryTab(it)) },
        canExecute = state.canExecuteCommand,
        onExecute = { command -> onAction(CommandAction.ExecuteCommand(command)) },
    )
}

@Composable
private fun CommandScreen(
    commands: NormalCommandGroup,
    filtered: NormalCommandCategory,
    onClickFilter: (NormalCommandCategory) -> Unit,
    canExecute: Boolean,
    onExecute: (NormalCommand) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        CommandTab(
            filtered = filtered,
            onClick = onClickFilter,
            modifier = Modifier.height(48.dp).padding(8.dp),
        )

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
            modifier = Modifier.fillMaxSize().padding(16.dp),
        )
    }
}

@Preview
@Composable
private fun CommandScreen_Preview() {
    CommandScreen(
        commands =
            NormalCommandGroup(
                listOf(NormalCommand.DarkThemeOn(), NormalCommand.DarkThemeOff(), NormalCommand.WifiOn()),
                listOf(NormalCommand.DarkThemeOn(), NormalCommand.DarkThemeOff()),
                listOf(NormalCommand.WifiOn()),
            ),
        filtered = NormalCommandCategory.ALL,
        onClickFilter = {},
        canExecute = true,
        onExecute = {},
    )
}
