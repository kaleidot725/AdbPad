package jp.kaleidot725.adbpad.ui.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun DefaultOutlineTextField(
    id: Any? = null,
    label: String,
    initialText: String,
    placeHolder: String,
    isError: Boolean,
    onUpdateText: (String) -> Unit,
    maxLines: Int = 1,
    modifier: Modifier = Modifier,
) {
    var localText by remember(id) { mutableStateOf(initialText) }
    OutlinedTextField(
        label = { Text(label) },
        placeholder = { Text(placeHolder) },
        value = localText,
        onValueChange = {
            localText = it
            onUpdateText(it)
        },
        maxLines = maxLines,
        textStyle = TextStyle(color = MaterialTheme.colors.onSurface, fontSize = 16.sp),
        isError = isError,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun Preview() {
    DefaultOutlineTextField(
        initialText = "",
        placeHolder = "Search",
        onUpdateText = {},
        label = "LABEL",
        isError = false,
    )
}
