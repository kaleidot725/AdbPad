package jp.kaleidot725.adbpad.ui.screen.screenshot.component

import androidx.compose.foundation.ContextMenuItem
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.screenshot.Screenshot
import jp.kaleidot725.adbpad.ui.common.resource.clickableBackground
import jp.kaleidot725.adbpad.ui.component.menu.ThemedContextMenuArea

@Composable
fun ScreenshotExplorer(
    selectedScreenshot: Screenshot,
    screenshots: List<Screenshot>,
    onSelectScreenShot: (Screenshot) -> Unit,
    onDeleteScreenshot: (Screenshot) -> Unit,
    onNextScreenshot: () -> Unit,
    onPreviousScreenshot: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        if (screenshots.isNotEmpty()) {
            val lazyColumnState = rememberLazyListState()
            LazyColumn(
                state = lazyColumnState,
                modifier =
                    Modifier
                        .padding(4.dp)
                        .onKeyEvent { event ->
                            when {
                                event.key == Key.DirectionUp && event.type == KeyEventType.KeyDown -> {
                                    onPreviousScreenshot()
                                    true
                                }

                                event.key == Key.DirectionDown && event.type == KeyEventType.KeyDown -> {
                                    onNextScreenshot()
                                    true
                                }

                                else -> false
                            }
                        },
            ) {
                items(
                    items = screenshots,
                    key = { screenshot -> screenshot.file?.absolutePath ?: "" },
                ) { screenshot ->
                    ThemedContextMenuArea(
                        items = {
                            listOf(
                                ContextMenuItem(
                                    label = Language.delete,
                                    onClick = { onDeleteScreenshot(screenshot) },
                                ),
                            )
                        },
                    ) {
                        Row(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .clickableBackground(
                                        isSelected = selectedScreenshot == screenshot,
                                        shape = RoundedCornerShape(4.dp),
                                    ).clickable { onSelectScreenShot(screenshot) }
                                    .padding(horizontal = 12.dp, vertical = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Text(
                                text = screenshot.file?.name ?: "",
                            )
                        }
                    }
                }
            }

            VerticalScrollbar(
                modifier = Modifier.align(Alignment.CenterEnd).width(8.dp).fillMaxHeight(),
                adapter = rememberScrollbarAdapter(scrollState = lazyColumnState),
            )
        } else {
            Text(
                text = "Not Found Screenshot",
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}
