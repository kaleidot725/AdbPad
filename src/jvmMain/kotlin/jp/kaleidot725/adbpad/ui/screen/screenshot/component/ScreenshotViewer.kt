package jp.kaleidot725.adbpad.ui.screen.screenshot.component

import androidx.compose.animation.core.tween
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
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
import jp.kaleidot725.adbpad.domain.model.screenshot.Screenshot
import jp.kaleidot725.adbpad.ui.common.resource.UserColor
import jp.kaleidot725.adbpad.ui.common.resource.defaultBorder
import jp.kaleidot725.adbpad.ui.component.button.CommandIconButton
import jp.kaleidot725.adbpad.ui.component.button.CommandTextButton
import kotlinx.coroutines.launch
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@Composable
fun ScreenshotViewer(
    screenshot: Screenshot,
    isCapturing: Boolean,
    onOpenDirectory: () -> Unit,
    onCopyScreenshot: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        ScreenshotActions(
            enabled = screenshot.file != null,
            onOpen = onOpenDirectory,
            onCopy = onCopyScreenshot,
            modifier = Modifier.height(48.dp).padding(horizontal = 12.dp).align(Alignment.End),
        )

        Divider(modifier = Modifier.height(1.dp).fillMaxWidth().defaultBorder())

        if (isCapturing) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            if (screenshot.file != null) {
                val zoomState = rememberZoomState()
                val coroutineScope = rememberCoroutineScope()
                BoxWithConstraints(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val constraints = this

                    AsyncImage(
                        model = screenshot.file,
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
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = Language.notFoundScreenshot,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ScreenshotViewer_Preview() {
    ScreenshotViewer(
        screenshot = Screenshot(null),
        isCapturing = false,
        onOpenDirectory = {},
        onCopyScreenshot = {},
        modifier = Modifier.fillMaxSize(),
    )
}
