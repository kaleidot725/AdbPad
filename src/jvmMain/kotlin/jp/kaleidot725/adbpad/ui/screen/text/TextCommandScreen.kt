package jp.kaleidot725.adbpad.ui.screen.text

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.UserColor
import jp.kaleidot725.adbpad.ui.common.resource.defaultBorder
import jp.kaleidot725.adbpad.ui.screen.screenshot.cursorForHorizontalResize
import jp.kaleidot725.adbpad.ui.screen.text.component.TextCommandActions
import jp.kaleidot725.adbpad.ui.screen.text.component.TextCommandEditor
import jp.kaleidot725.adbpad.ui.screen.text.component.TextCommandHeader
import jp.kaleidot725.adbpad.ui.screen.text.component.TextCommandList
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.HorizontalSplitPane
import org.jetbrains.compose.splitpane.SplitPaneState
import org.jetbrains.compose.splitpane.rememberSplitPaneState

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
fun TextCommandScreen(
    state: TextCommandState,
    onAction: (TextCommandAction) -> Unit,
    splitterState: SplitPaneState,
) {
    HorizontalSplitPane(
        splitPaneState = splitterState,
        modifier = Modifier.fillMaxSize(),
    ) {
        first(minSize = 350.dp) {
            Column {
                TextCommandHeader(
                    searchText = state.searchText,
                    onUpdateSearchText = { onAction(TextCommandAction.UpdateSearchText(it)) },
                    onAddNewTextCommand = { onAction(TextCommandAction.AddNewText) },
                )

                Divider(modifier = Modifier.fillMaxWidth().defaultBorder())

                TextCommandList(
                    selectedCommand = state.selectedCommand,
                    commands = state.commands,
                    onSelectCommand = { onAction(TextCommandAction.SelectCommand(it)) },
                    onNextCommand = { onAction(TextCommandAction.NextCommand) },
                    onPreviousCommand = { onAction(TextCommandAction.PreviousCommand) },
                    modifier = Modifier.fillMaxSize().padding(top = 2.dp),
                )
            }
        }

        second {
            Column(modifier = Modifier.background(MaterialTheme.colors.surface)) {
                if (state.selectedCommand != null) {
                    TextCommandEditor(
                        command = state.selectedCommand,
                        onUpdateTitle = { id, title -> onAction(TextCommandAction.UpdateCommandTitle(id, title)) },
                        onUpdateText = { id, text -> onAction(TextCommandAction.UpdateCommandText(id, text)) },
                        onDelete = { onAction(TextCommandAction.DeleteSelectedCommandText) },
                    )

                    Spacer(
                        modifier = Modifier.weight(1.0f),
                    )

                    TextCommandActions(
                        command = state.selectedCommand,
                        canSend = state.canSend,
                        onSendText = { onAction(TextCommandAction.SendTextCommand) },
                        selectedOption = state.selectedTextCommandOption,
                        onUpdateTextCommandOption = { onAction(TextCommandAction.UpdateTextCommandOption(it)) },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }

        splitter {
            visiblePart {
                Box(
                    Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                        .border(BorderStroke(1.dp, UserColor.getSplitterColor())),
                )
            }

            handle {
                Box(
                    Modifier
                        .markAsHandle()
                        .cursorForHorizontalResize()
                        .width(10.dp)
                        .fillMaxHeight()
                        .markAsHandle(),
                )
            }
        }
    }
}

@OptIn(ExperimentalSplitPaneApi::class)
@Preview
@Composable
private fun InputTextScreen_Preview() {
    TextCommandScreen(
        state = TextCommandState(),
        onAction = {},
        splitterState = rememberSplitPaneState(),
    )
}
