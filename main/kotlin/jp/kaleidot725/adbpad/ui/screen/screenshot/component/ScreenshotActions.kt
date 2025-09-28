package jp.kaleidot725.adbpad.ui.screen.screenshot.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Copy
import com.composables.icons.lucide.ExternalLink
import com.composables.icons.lucide.FolderOpen
import com.composables.icons.lucide.Lucide

@Composable
fun ScreenshotActions(
    hasPreview: Boolean,
    onOpenPreview: () -> Unit,
    onCopyScreenshot: () -> Unit,
    onOpenDirectory: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier,
    ) {
        IconButton(
            onClick = onCopyScreenshot,
            enabled = hasPreview,
            modifier =
                Modifier
                    .padding(vertical = 4.dp)
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.CenterVertically),
        ) {
            Icon(
                imageVector = Lucide.Copy,
                contentDescription = "copy screenshot",
                modifier = Modifier.size(20.dp),
            )
        }

        IconButton(
            onClick = onOpenPreview,
            enabled = hasPreview,
            modifier =
                Modifier
                    .padding(vertical = 4.dp)
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.CenterVertically),
        ) {
            Icon(
                imageVector = Lucide.ExternalLink,
                contentDescription = "open preview",
                modifier = Modifier.size(20.dp),
            )
        }

        IconButton(
            onClick = onOpenDirectory,
            modifier =
                Modifier
                    .padding(vertical = 4.dp)
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.CenterVertically),
        ) {
            Icon(
                imageVector = Lucide.FolderOpen,
                contentDescription = "open directory",
                modifier = Modifier.size(20.dp),
            )
        }
    }
}

@Preview
@Composable
private fun ScreenshotHeader_Preview() {
    ScreenshotActions(
        hasPreview = true,
        onOpenPreview = {},
        onCopyScreenshot = {},
        onOpenDirectory = {},
        modifier = Modifier.background(Color.Gray),
    )
}
