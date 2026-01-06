package jp.kaleidot725.adbpad.ui.screen.text.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.component.card.CommonItemCard
import jp.kaleidot725.adbpad.ui.component.menu.ThemedContextMenuArea
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
    val dateFormat = remember { SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()) }

    Box(modifier = modifier) {
        if (commands.isNotEmpty()) {
            val lazyColumnState = rememberLazyListState()
            LazyColumn(
                state = lazyColumnState,
                modifier =
                    Modifier
                        .fillMaxWidth()
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
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding =
                    PaddingValues(
                        start = 8.dp,
                        top = 4.dp,
                        end = 8.dp,
                        bottom = 24.dp,
                    ),
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
                        CommonItemCard(
                            title = command.title.ifEmpty { Language.textCommandUnTitle },
                            details = dateFormat.format(Date(command.lastModified)),
                            isSelected = selectedCommand?.id == command.id,
                            onClick = { onSelectCommand(command) },
                        )
                    }
                }
            }
        } else {
            Text(
                text = Language.notFoundInputText,
                modifier = Modifier.align(Alignment.Center),
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
