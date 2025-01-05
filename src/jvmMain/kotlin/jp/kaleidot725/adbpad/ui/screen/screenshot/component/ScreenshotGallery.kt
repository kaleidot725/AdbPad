package jp.kaleidot725.adbpad.ui.screen.screenshot.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import jp.kaleidot725.adbpad.domain.model.UserColor
import jp.kaleidot725.adbpad.domain.model.screenshot.Screenshot

@Composable
fun ScreenshotGallery(
    selectedScreenshot: Screenshot,
    screenshots: List<Screenshot>,
    onSelectScreenShot: (Screenshot) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(vertical = 16.dp),
        ) {
            items(
                items = screenshots,
                key = { screenshot -> screenshot.file?.absolutePath ?: "" },
            ) { screenshot ->

                AsyncImage(
                    model = screenshot.file,
                    contentDescription = null,
                    modifier =
                        Modifier
                            .size(100.dp)
                            .clickable { onSelectScreenShot(screenshot) }
                            .border(
                                border =
                                    BorderStroke(
                                        width = 2.dp,
                                        color =
                                            if (selectedScreenshot == screenshot) {
                                                MaterialTheme.colors.primary
                                            } else {
                                                UserColor.getSplitterColor()
                                            },
                                    ),
                                shape = RoundedCornerShape(4.dp),
                            ),
                )
            }
        }
    }
}