package jp.kaleidot725.adbpad.ui.screen.menu.screenshot.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ScreenshotHeader(
    name: String,
    enabled: Boolean,
    onCopy: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier,
    ) {
        Text(
            text = name,
            modifier =
                Modifier
                    .weight(1.0f)
                    .align(Alignment.CenterVertically),
        )

        IconButton(
            onClick = onCopy,
            enabled = enabled,
            modifier =
                Modifier
                    .padding(vertical = 4.dp)
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = Icons.Default.FileCopy,
                contentDescription = "copy",
                modifier = Modifier.height(20.dp),
            )
        }

        IconButton(
            onClick = onDelete,
            enabled = enabled,
            modifier =
                Modifier
                    .padding(vertical = 4.dp)
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "delete",
                modifier = Modifier.height(20.dp),
            )
        }
    }
}

@Preview
@Composable
private fun ScreenshotHeader_Preview() {
    ScreenshotHeader(
        name = "TEST",
        enabled = true,
        onCopy = {},
        onDelete = {},
        modifier =
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Gray),
    )
}
