package jp.kaleidot725.adbpad.view.component.command

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.language.Language

@Composable
fun CommandList(
    commands: List<NormalCommand>,
    canExecute: Boolean,
    onExecute: (NormalCommand) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        if (commands.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(250.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(commands) { command ->
                    CommandItem(
                        title = command.title,
                        detail = command.details,
                        isRunning = command.isRunning,
                        canExecute = canExecute,
                        onExecute = { onExecute(command) },
                        modifier = Modifier.height(150.dp).fillMaxWidth().padding(2.dp),
                    )
                }
            }
        } else {
            Text(
                text = Language.notFoundCommand,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Preview
@Composable
private fun CommandList_Preview() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CommandList(
            commands = listOf(NormalCommand.DarkThemeOn(), NormalCommand.DarkThemeOff(), NormalCommand.WifiOn()),
            canExecute = true,
            onExecute = {},
            modifier = Modifier.fillMaxWidth().weight(0.5f),
        )

        CommandList(
            commands = emptyList(),
            canExecute = true,
            onExecute = {},
            modifier = Modifier.fillMaxWidth().weight(0.5f).background(Color.LightGray),
        )
    }
}
