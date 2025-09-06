package jp.kaleidot725.adbpad.ui.screen.text.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Plus
import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground
import jp.kaleidot725.adbpad.ui.component.menu.ThemedContextMenuArea

@Composable
fun TextCommandList(
    selectedCommand: TextCommand?,
    commands: List<TextCommand>,
    onSelectCommand: (TextCommand) -> Unit,
    onDeleteCommand: (TextCommand) -> Unit,
    onAddNewTextCommand: () -> Unit,
    onNextCommand: () -> Unit,
    onPreviousCommand: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        if (commands.isNotEmpty()) {
            val lazyColumnState = rememberLazyListState()
            LazyColumn(
                state = lazyColumnState,
                modifier =
                    Modifier
                        .padding(4.dp)
                        .onKeyEvent { event ->
                            when {
                                event.key == Key.DirectionUp && event.type == KeyEventType.KeyDown -> {
                                    onPreviousCommand()
                                    true
                                }
                                event.key == Key.DirectionDown && event.type == KeyEventType.KeyDown -> {
                                    onNextCommand()
                                    true
                                }
                                else -> false
                            }
                        },
            ) {
                items(
                    items = commands,
                ) { command ->
                    ThemedContextMenuArea(
                        items = {
                            listOf(
                                ContextMenuItem(
                                    label = Language.delete,
                                    onClick = { onDeleteCommand(command) },
                                ),
                            )
                        },
                    ) {
                        Row(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .clickableBackground(
                                        isSelected = selectedCommand?.id == command.id,
                                        shape = RoundedCornerShape(4.dp),
                                    ).clickable { onSelectCommand(command) }
                                    .padding(horizontal = 12.dp, vertical = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Text(text = command.title.ifEmpty { Language.textCommandUnTitle })
                        }
                    }
                }
            }
        } else {
            Text(
                text = Language.notFoundInputText,
                modifier = Modifier.align(Alignment.Center),
            )
        }

        FloatingActionButton(
            onClick = onAddNewTextCommand,
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            modifier =
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
        ) {
            Icon(
                imageVector = Lucide.Plus,
                contentDescription = "Add new text command",
            )
        }
    }
}

@Preview
@Composable
private fun TextCommandList_Preview() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TextCommandList(
            selectedCommand = TextCommand(title = "Title", text = "Text"),
            commands = listOf(TextCommand(title = "Title", text = "Text"), TextCommand(title = "Title", text = "Text")),
            onSelectCommand = {},
            onDeleteCommand = {},
            onAddNewTextCommand = {},
            onNextCommand = {},
            onPreviousCommand = {},
            modifier = Modifier.fillMaxWidth().weight(0.5f, true),
        )

        TextCommandList(
            selectedCommand = TextCommand(title = "Title", text = "Text"),
            commands = emptyList(),
            onSelectCommand = {},
            onDeleteCommand = {},
            onAddNewTextCommand = {},
            onNextCommand = {},
            onPreviousCommand = {},
            modifier = Modifier.fillMaxWidth().weight(0.5f, true).background(Color.LightGray),
        )
    }
}
