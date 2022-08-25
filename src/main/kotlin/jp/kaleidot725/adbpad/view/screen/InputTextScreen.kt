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
import jp.kaleidot725.adbpad.model.data.InputText
import jp.kaleidot725.adbpad.view.common.input.InputTextActionMenu
import jp.kaleidot725.adbpad.view.common.input.InputTextList

@Composable
fun InputTextScreen(
    inputText: InputText,
    onTextChange: (InputText) -> Unit,
    inputTexts: List<InputText>,
    onExecute: (InputText) -> Unit,
    onSave: (InputText) -> Unit,
    onDelete: (InputText) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        InputTextList(
            inputTexts = inputTexts,
            onExecute = onExecute,
            onDelete = onDelete,
            modifier = Modifier.fillMaxSize().padding(bottom = 60.dp)
        )

        InputTextActionMenu(
            inputText = inputText,
            onTextChange = onTextChange,
            onExecute = onExecute,
            onSave = onSave,
            modifier = Modifier.height(50.dp).fillMaxWidth().align(Alignment.BottomEnd)
        )
    }
}

@Preview
@Composable
private fun InputTextScreen_Preview() {
    InputTextScreen(
        inputText = InputText("SAMPLE INPUT TEXT"),
        onTextChange = {},
        inputTexts = listOf(InputText("A"), InputText("B"), InputText("C")),
        onExecute = {},
        onSave = {},
        onDelete = {}
    )
}
