package jp.kaleidot725.adbpad.view.screen.input

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
import jp.kaleidot725.adbpad.domain.model.InputTextCommand
import jp.kaleidot725.adbpad.view.component.input.InputTextActionMenu
import jp.kaleidot725.adbpad.view.component.input.InputTextCommandList

@Composable
fun InputTextScreen(
    // InputText
    inputText: String,
    onTextChange: (String) -> Unit,
    onSendInputText: () -> Unit,
    canSendInputText: Boolean,
    onSaveInputText: () -> Unit,
    canSaveInputText: Boolean,

    // Commands
    commands: List<InputTextCommand>,
    onSendCommand: (InputTextCommand) -> Unit,
    canSendCommand: Boolean,
    onDeleteCommand: (InputTextCommand) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        InputTextCommandList(
            commands = commands,
            onSend = onSendCommand,
            canSend = canSendCommand,
            onDelete = onDeleteCommand,
            modifier = Modifier.fillMaxSize().padding(bottom = 60.dp)
        )

        InputTextActionMenu(
            inputText = inputText,
            onTextChange = onTextChange,
            onSend = onSendInputText,
            canSend = canSendInputText,
            onSave = onSaveInputText,
            canSave = canSaveInputText,
            modifier = Modifier.height(50.dp).fillMaxWidth().align(Alignment.BottomEnd)
        )
    }
}

@Preview
@Composable
private fun InputTextScreen_Preview() {
    InputTextScreen(
        inputText = "SAMPLE INPUT TEXT",
        onTextChange = {},
        onSendInputText = {},
        onSaveInputText = {},
        canSaveInputText = true,
        commands = listOf(InputTextCommand("TEST1"), InputTextCommand("TEST2")),
        onSendCommand = {},
        canSendCommand = true,
        canSendInputText = true,
        onDeleteCommand = {}
    )
}
