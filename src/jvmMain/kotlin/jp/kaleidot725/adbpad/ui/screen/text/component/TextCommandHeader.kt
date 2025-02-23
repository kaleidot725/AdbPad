package jp.kaleidot725.adbpad.ui.screen.text.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextCommandHeader(
    searchText: String,
    onUpdateSearchText: (String) -> Unit,
    onAddNewTextCommand: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterVertically).padding(12.dp)
        )

        Box(
            modifier = Modifier.align(Alignment.CenterVertically).weight(1.0f)
        ) {
            if (searchText.isEmpty()) {
                Text(
                    text = "Search",
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                    fontSize = 16.sp,
                    modifier = Modifier
                )
            }
            BasicTextField(
                value = searchText,
                onValueChange = onUpdateSearchText,
                maxLines = 1,
                textStyle = TextStyle(color = MaterialTheme.colors.onSurface, fontSize = 16.sp),
                cursorBrush = SolidColor(MaterialTheme.colors.onSurface),
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }

        IconButton(
            onClick = onAddNewTextCommand
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    TextCommandHeader(
        searchText = "TEST",
        onUpdateSearchText = {},
        onAddNewTextCommand = {},
    )
}