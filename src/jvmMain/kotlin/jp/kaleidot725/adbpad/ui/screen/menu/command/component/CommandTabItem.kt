package jp.kaleidot725.adbpad.ui.screen.menu.command.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground

@Composable
fun CommandTabItem(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .widthIn(min = 200.dp)
            .heightIn(min = 40.dp)
            .clickableBackground(
                isSelected = isSelected,
                selectedColor = Color.Transparent,
            )
            .clickable(onClick = onClick),
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.align(Alignment.Center),
        )
        if (isSelected) {
            Box(
                modifier =
                    Modifier
                        .height(2.dp)
                        .widthIn(200.dp)
                        .background(MaterialTheme.colors.primary)
                        .align(Alignment.BottomEnd),
            )
        }
    }
}

@Preview
@Composable
private fun CommandTabItemPreview() {
    Row {
        CommandTabItem("one", false, {})
        CommandTabItem("two", true, {})
    }
}
