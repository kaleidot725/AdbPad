package jp.kaleidot725.adbpad.ui.screen.screenshot.component

import androidx.compose.animation.core.tween
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Minus
import com.composables.icons.lucide.Plus
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.ui.common.resource.UserColor
import jp.kaleidot725.adbpad.ui.component.button.CommandIconButton
import jp.kaleidot725.adbpad.ui.component.button.CommandTextButton
import kotlinx.coroutines.launch
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable
import java.io.File

@Composable
fun ScreenshotViewer(
    file: File?,
    isCapturing: Boolean,
    onOpenPreview: () -> Unit,
    onOpenDirectory: () -> Unit,
    onCopyScreenshot: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        when {
            isCapturing -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            file != null -> {
                val zoomState = rememberZoomState()
                val coroutineScope = rememberCoroutineScope()
                BoxWithConstraints(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val constraints = this

                    AsyncImage(
                        model = file,
                        contentDescription = null,
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .clip(RectangleShape)
                                .zoomable(zoomState),
                    )

                    Column(
                        modifier =
                            Modifier
                                .align(Alignment.BottomEnd)
                                .padding(12.dp)
                                .background(
                                    color = UserColor.getFloatingBackgroundColor(),
                                    shape = RoundedCornerShape(4.dp),
                                ),
                    ) {
                        CommandIconButton(
                            image = Lucide.Plus,
                            onClick = {
                                coroutineScope.launch {
                                    zoomState.changeScale(
                                        targetScale = zoomState.scale + 0.5f,
                                        position =
                                            Offset(
                                                constraints.maxWidth.value / 2f,
                                                constraints.maxHeight.value / 2f,
                                            ),
                                        animationSpec = tween(),
                                    )
                                }
                            },
                            modifier =
                                Modifier
                                    .padding(4.dp)
                                    .size(32.dp),
                        )

                        CommandIconButton(
                            image = Lucide.Minus,
                            onClick = {
                                coroutineScope.launch {
                                    zoomState.changeScale(
                                        targetScale = zoomState.scale - 0.5f,
                                        position =
                                            Offset(
                                                constraints.maxWidth.value / 2f,
                                                constraints.maxHeight.value / 2f,
                                            ),
                                        animationSpec = tween(),
                                    )
                                }
                            },
                            modifier =
                                Modifier
                                    .padding(4.dp)
                                    .size(32.dp),
                        )

                        CommandTextButton(
                            text = "100%",
                            onClick = {
                                coroutineScope.launch {
                                    coroutineScope.launch {
                                        zoomState.reset()
                                    }
                                }
                            },
                            modifier =
                                Modifier
                                    .padding(4.dp)
                                    .size(32.dp),
                        )
                    }
                }
            }

            else -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = Language.notFoundScreenshot,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }
        }

        ScreenshotActions(
            hasPreview = file != null,
            onOpenPreview = onOpenPreview,
            onCopyScreenshot = onCopyScreenshot,
            onOpenDirectory = onOpenDirectory,
            modifier =
                Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .background(
                        color = UserColor.getFloatingBackgroundColor(),
                        shape = RoundedCornerShape(8.dp),
                    ).padding(horizontal = 8.dp, vertical = 4.dp),
        )
    }
}

@Preview
@Composable
private fun ScreenshotViewer_Preview() {
    ScreenshotViewer(
        file = null,
        isCapturing = false,
        onOpenPreview = {},
        onCopyScreenshot = {},
        onOpenDirectory = {},
        modifier = Modifier.fillMaxSize(),
    )
}
