package jp.kaleidot725.adbpad.view.component.command

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.model.Command
import jp.kaleidot725.adbpad.model.toDetail
import jp.kaleidot725.adbpad.model.toTitle

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CommandList(
    commands: List<Command>,
    onExecuteCommand: (Command) -> Unit,
    minSize: Dp = 200.dp,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize),
        verticalArrangement = verticalArrangement,
        horizontalArrangement = horizontalArrangement,
        modifier = modifier
    ) {
        items(commands) { command ->
            CommandItem(
                title = command.toTitle(),
                detail = command.toDetail(),
                onExecute = { onExecuteCommand(command) },
                modifier = Modifier.size(minSize)
            )
        }
    }
}