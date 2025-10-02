package jp.kaleidot725.adbpad.ui.screen.screenshot.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Copy
import com.composables.icons.lucide.Folder
import com.composables.icons.lucide.ImageUp
import com.composables.icons.lucide.Lucide

@Composable
fun ScreenshotActions(
    enabled: Boolean,
    onOpen: () -> Unit,
    onEdit: () -> Unit,
    onCopy: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        ActionIcon(
            onClick = onOpen,
            enabled = enabled,
            image = Lucide.Folder,
        )

        ActionIcon(
            onClick = onEdit,
            enabled = enabled,
            image = Lucide.ImageUp,
        )

        ActionIcon(
            onClick = onCopy,
            enabled = enabled,
            image = Lucide.Copy,
        )
    }
}

@Composable
private fun ActionIcon(
    onClick: () -> Unit,
    enabled: Boolean,
    image: ImageVector,
) {
    IconButton(
        onClick = onClick,
        enabled = enabled,
        modifier =
            Modifier
                .padding(vertical = 4.dp)
                .size(32.dp)
                .clip(RoundedCornerShape(8.dp)),
    ) {
        Icon(
            imageVector = image,
            contentDescription = null,
            modifier = Modifier.height(20.dp),
        )
    }
}

@Preview
@Composable
private fun ScreenshotHeader_Preview() {
    ScreenshotActions(
        enabled = true,
        onOpen = {},
        onEdit = {},
        onCopy = {},
        modifier =
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Gray),
    )
}
