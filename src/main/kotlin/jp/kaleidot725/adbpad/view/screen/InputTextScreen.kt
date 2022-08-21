package jp.kaleidot725.adbpad.view.page

import androidx.compose.foundation.layout.Arrangement
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
import jp.kaleidot725.adbpad.view.component.input.InputTestList
import jp.kaleidot725.adbpad.view.component.input.InputTextFields

@Composable
fun InputTextScreen(
    inputText: InputText,
    onTextChange: (InputText) -> Unit,
    texts: List<InputText>,
    onExecute: (InputText) -> Unit,
    onSave: (InputText) -> Unit,
    onDelete: (InputText) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        InputTestList(
            texts = texts,
            onExecute = onExecute,
            minSize = 200.dp,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        )

        InputTextFields(
            inputText = inputText,
            onTextChange = onTextChange,
            onExecute = onExecute,
            onSave = onSave,
            modifier = Modifier.height(50.dp).fillMaxWidth().align(Alignment.BottomEnd)
        )
    }
}
