package jp.kaleidot725.adbpad.ui.component.text

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun DefaultTextField(
    id: Any? = null,
    initialText: String,
    placeHolder: String,
    onUpdateText: (String) -> Unit,
    maxLines: Int = 1,
    modifier: Modifier = Modifier,
) {
    var localText by remember(id) { mutableStateOf(initialText) }

    Box(modifier) {
        if (localText.isEmpty()) {
            Text(
                text = placeHolder,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterStart),
            )
        }

        BasicTextField(
            value = localText,
            onValueChange = {
                localText = it
                onUpdateText(it)
            },
            maxLines = maxLines,
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface, fontSize = 16.sp),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
            modifier = Modifier.align(Alignment.CenterStart),
        )
    }
}

@Preview
@Composable
private fun Preview() {
    DefaultTextField(
        initialText = "",
        placeHolder = "Search",
        onUpdateText = {},
    )
}
