package jp.kaleidot725.adbpad.ui.screen.menu.text

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.command.TextCommand
import jp.kaleidot725.adbpad.ui.screen.menu.text.component.InputTextActionMenu
import jp.kaleidot725.adbpad.ui.screen.menu.text.component.TextCommandList

@Composable
fun TextCommandScreen(
    // InputText
    inputText: String,
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
    commands: List<TextCommand>,
    onSendCommand: (TextCommand) -> Unit,
    canSendCommand: Boolean,
    onDeleteCommand: (TextCommand) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextCommandList(
            commands = commands,
            onSend = onSendCommand,
            canSend = canSendCommand,
            onDelete = onDeleteCommand,
            modifier = Modifier.fillMaxSize().padding(bottom = 60.dp),
        )

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
            modifier = Modifier.height(50.dp).fillMaxWidth().align(Alignment.BottomEnd),
        )
    }
}

@Preview
@Composable
private fun InputTextScreen_Preview() {
    TextCommandScreen(
        inputText = "SAMPLE INPUT TEXT",
        onTextChange = {},
        isSendingInputText = false,
        onSendInputText = {},
        onSaveInputText = {},
        canSaveInputText = true,
        commands = listOf(TextCommand("TEST1"), TextCommand("TEST2")),
        onSendCommand = {},
        isSendingTab = false,
        canSendCommand = true,
        canSendInputText = true,
        onDeleteCommand = {},
        canSendTabKey = false,
        onSendTabKey = {},
    )
}
