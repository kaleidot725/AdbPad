package jp.kaleidot725.adbpad.ui.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandCategory
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandGroup
import jp.kaleidot725.adbpad.ui.screen.menu.command.component.CommandList
import jp.kaleidot725.adbpad.ui.screen.menu.command.component.CommandTab
import jp.kaleidot725.adbpad.ui.screen.menu.command.component.CommandTabItem

@Composable
fun CommandScreen(
    commands: NormalCommandGroup,
    filtered: NormalCommandCategory?,
    onClickFilter: (NormalCommandCategory?) -> Unit,
    canExecute: Boolean,
    onExecute: (NormalCommand) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        CommandTab(
            filtered,
            onClickFilter,
        )
        CommandList(
            commands = when(filtered) {
                NormalCommandCategory.UI -> commands.ui
                NormalCommandCategory.Communication -> commands.communication
                null -> commands.all
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
        commands = NormalCommandGroup(
            listOf(NormalCommand.DarkThemeOn(), NormalCommand.DarkThemeOff(), NormalCommand.WifiOn()),
            listOf(NormalCommand.DarkThemeOn(), NormalCommand.DarkThemeOff()),
            listOf(NormalCommand.WifiOn()),
        ),
        filtered = null,
        onClickFilter = {},
        canExecute = true,
        onExecute = {},
    )
}
