package jp.kaleidot725.adbpad.ui.screen.text

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.UserColor
import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.ui.common.resource.defaultBorder
import jp.kaleidot725.adbpad.ui.screen.screenshot.cursorForHorizontalResize
import jp.kaleidot725.adbpad.ui.screen.text.component.InputTextActionMenu
import jp.kaleidot725.adbpad.ui.screen.text.component.TextCommandHeader
import jp.kaleidot725.adbpad.ui.screen.text.component.TextCommandList
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.HorizontalSplitPane
import org.jetbrains.compose.splitpane.SplitPaneState
import org.jetbrains.compose.splitpane.rememberSplitPaneState

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
fun TextCommandScreen(
    // InputText
    searchText: String,
    onUpdateSearchText: (String) -> Unit,
    onAddNewTextCommand: () -> Unit,
    inputText: String,
    splitterState: SplitPaneState,
    onTextChange: (String) -> Unit,
    isSendingInputText: Boolean,
    onSendInputText: () -> Unit,
    canSendInputText: Boolean,
    canSendTabKey: Boolean,
    onSendTabKey: () -> Unit,
    isSendingTab: Boolean,
    // Commands
    selectedCommand: TextCommand,
    commands: List<TextCommand>,
    onSelectCommand: (TextCommand) -> Unit,
    onNextCommand: () -> Unit,
    onPreviousCommand: () -> Unit,
) {
    HorizontalSplitPane(
        splitPaneState = splitterState,
        modifier = Modifier.fillMaxSize(),
    ) {
        first(minSize = 350.dp) {
            Column {
                TextCommandHeader(
                    searchText = searchText,
                    onUpdateSearchText = onUpdateSearchText,
                    onAddNewTextCommand = onAddNewTextCommand,
                )

                Divider(modifier = Modifier.fillMaxWidth().defaultBorder())

                TextCommandList(
                    selectedCommand = selectedCommand,
                    commands = commands,
                    onSelectCommand = onSelectCommand,
                    onNextCommand = onNextCommand,
                    onPreviousCommand = onPreviousCommand,
                    modifier = Modifier.fillMaxSize().padding(top = 2.dp),
                )
            }
        }

        second {
            Column {
                InputTextActionMenu(
                    inputText = inputText,
                    onTextChange = onTextChange,
                    isSending = isSendingInputText,
                    onSend = onSendInputText,
                    canSend = canSendInputText,
                    onSendTab = onSendTabKey,
                    isSendingTag = isSendingTab,
                    canSendTab = canSendTabKey,
                    modifier = Modifier.height(50.dp).fillMaxWidth(),
                )
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
        searchText = "SAMPLE SEARCH TEXT",
        onUpdateSearchText = {},
        onAddNewTextCommand = {},
        inputText = "SAMPLE INPUT TEXT",
        splitterState = rememberSplitPaneState(),
        onTextChange = {},
        isSendingInputText = false,
        onSendInputText = {},
        selectedCommand = TextCommand(title = "TITLE", text = "TEXT"),
        commands = listOf(TextCommand(title = "TITLE", text = "TEXT"), TextCommand(title = "TITLE", text = "TEXT")),
        onSelectCommand = {},
        onNextCommand = {},
        onPreviousCommand = {},
        isSendingTab = false,
        canSendInputText = true,
        canSendTabKey = false,
        onSendTabKey = {},
    )
}
