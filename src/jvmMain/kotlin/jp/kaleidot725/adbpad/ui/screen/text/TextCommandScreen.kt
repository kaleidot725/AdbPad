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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.UserColor
import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.ui.screen.screenshot.component.ScreenshotExplorer
import jp.kaleidot725.adbpad.ui.screen.screenshot.component.ScreenshotMenu
import jp.kaleidot725.adbpad.ui.screen.screenshot.component.ScreenshotViewer
import jp.kaleidot725.adbpad.ui.screen.screenshot.cursorForHorizontalResize
import jp.kaleidot725.adbpad.ui.screen.text.component.InputTextActionMenu
import jp.kaleidot725.adbpad.ui.screen.text.component.TextCommandList
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.HorizontalSplitPane
import org.jetbrains.compose.splitpane.SplitPaneState
import org.jetbrains.compose.splitpane.rememberSplitPaneState

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
fun TextCommandScreen(
    // InputText
    inputText: String,
    splitterState: SplitPaneState,
    onTextChange: (String) -> Unit,
    isSendingInputText: Boolean,
    onSendInputText: () -> Unit,
    canSendInputText: Boolean,
    onSaveInputText: () -> Unit,
    canSaveInputText: Boolean,
    canSendTabKey: Boolean,
    onSendTabKey: () -> Unit,
    isSendingTab: Boolean,
    // Commands
    selectedCommand: TextCommand,
    commands: List<TextCommand>,
    onSelectCommand: (TextCommand) -> Unit,
    onNextCommand: () -> Unit,
    onPreviousCommand: () -> Unit,
    onSendCommand: (TextCommand) -> Unit,
    canSendCommand: Boolean,
    onDeleteCommand: (TextCommand) -> Unit,
) {
    HorizontalSplitPane(
        splitPaneState = splitterState,
        modifier = Modifier.fillMaxSize()
    ) {
        first(minSize = 350.dp) {
            TextCommandList(
                selectedCommand = selectedCommand,
                commands = commands,
                onSelectCommand = onSelectCommand,
                onNextCommand = onNextCommand,
                onPreviousCommand = onPreviousCommand,
                onSend = onSendCommand,
                canSend = canSendCommand,
                onDelete = onDeleteCommand,
                modifier = Modifier.fillMaxSize().padding(bottom = 60.dp),
            )
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
                    onSave = onSaveInputText,
                    canSave = canSaveInputText,
                    modifier = Modifier.height(50.dp).fillMaxWidth()
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
        inputText = "SAMPLE INPUT TEXT",
        splitterState = rememberSplitPaneState(),
        onTextChange = {},
        isSendingInputText = false,
        onSendInputText = {},
        onSaveInputText = {},
        canSaveInputText = true,
        selectedCommand = TextCommand("TEST1"),
        commands = listOf(TextCommand("TEST1"), TextCommand("TEST2")),
        onSelectCommand = {},
        onNextCommand = {},
        onPreviousCommand = {},
        onSendCommand = {},
        isSendingTab = false,
        canSendCommand = true,
        canSendInputText = true,
        onDeleteCommand = {},
        canSendTabKey = false,
        onSendTabKey = {},
    )
}
