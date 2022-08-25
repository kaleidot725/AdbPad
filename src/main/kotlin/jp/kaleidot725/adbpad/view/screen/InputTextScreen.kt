package jp.kaleidot725.adbpad.view.page

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
import jp.kaleidot725.adbpad.view.common.input.InputTextActionMenu
import jp.kaleidot725.adbpad.view.common.input.InputTextList

@Composable
fun InputTextScreen(
    inputText: String,
    onTextChange: (String) -> Unit,
    inputTexts: List<String>,
    onSend: (String) -> Unit,
    canSend: Boolean,
    onSave: (String) -> Unit,
    canSave: Boolean,
    onDelete: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        InputTextList(
            inputTexts = inputTexts,
            onSend = onSend,
            onDelete = onDelete,
            modifier = Modifier.fillMaxSize().padding(bottom = 60.dp)
        )

        InputTextActionMenu(
            inputText = inputText,
            onTextChange = onTextChange,
            onSend = onSend,
            canSend = canSend,
            onSave = onSave,
            canSave = canSave,
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
        inputTexts = listOf("A", "B", "C"),
        onSend = {},
        canSend = true,
        onSave = {},
        canSave = true,
        onDelete = {}
    )
}
