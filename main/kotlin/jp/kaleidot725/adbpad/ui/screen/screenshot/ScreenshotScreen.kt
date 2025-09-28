package jp.kaleidot725.adbpad.ui.screen.screenshot

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.model.language.Language
import jp.kaleidot725.adbpad.domain.model.sort.SortType
import jp.kaleidot725.adbpad.ui.common.resource.UserColor
import jp.kaleidot725.adbpad.ui.common.resource.defaultBorder
import jp.kaleidot725.adbpad.ui.component.explorer.DataFile as ExplorerDataFile
import jp.kaleidot725.adbpad.ui.component.explorer.Directory as ExplorerDirectory
import jp.kaleidot725.adbpad.ui.component.explorer.Explorer
import jp.kaleidot725.adbpad.ui.component.explorer.File as ExplorerFile
import jp.kaleidot725.adbpad.ui.component.menu.ThemedContextMenuArea
import jp.kaleidot725.adbpad.ui.screen.screenshot.component.ScreenshotHeader
import jp.kaleidot725.adbpad.ui.screen.screenshot.component.ScreenshotMenu
import jp.kaleidot725.adbpad.ui.screen.screenshot.component.ScreenshotViewer
import jp.kaleidot725.adbpad.ui.screen.screenshot.state.ScreenshotAction
import jp.kaleidot725.adbpad.ui.screen.screenshot.state.ScreenshotState
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.HorizontalSplitPane
import org.jetbrains.compose.splitpane.SplitPaneState
import org.jetbrains.compose.splitpane.rememberSplitPaneState
import java.awt.Cursor
import java.io.File

fun Modifier.cursorForHorizontalResize(): Modifier = pointerHoverIcon(PointerIcon(Cursor(Cursor.E_RESIZE_CURSOR)))

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
fun ScreenshotScreen(
    state: ScreenshotState,
    onAction: (ScreenshotAction) -> Unit,
    screenshotSplitPaneState: SplitPaneState,
) {
    ScreenshotScreen(
        selectedFile = state.preview,
        splitterState = screenshotSplitPaneState,
        files = state.previews,
        canCapture = state.canExecute,
        isCapturing = state.isCapturing,
        selectCommand = state.selectedCommand,
        commands = state.commands,
        searchText = state.searchText,
        sortType = state.sortType,
        onOpenPreview = {
            onAction(ScreenshotAction.OpenPreview)
        },
        onOpenDirectory = {
            onAction(ScreenshotAction.OpenDirectory)
        },
        onCopyScreenshot = {
            onAction(ScreenshotAction.CopyScreenshotToClipboard)
        },
        onDeleteScreenshot = {
            onAction(ScreenshotAction.DeleteScreenshotToClipboard)
        },
        onDeleteSpecificScreenshot = { file ->
            onAction(ScreenshotAction.DeleteScreenshot(file))
        },
        onTakeScreenshot = { command ->
            onAction(ScreenshotAction.TakeScreenshot(command))
        },
        onSelectScreenshot = { file ->
            onAction(ScreenshotAction.SelectScreenshot(file))
        },
        onNextScreenshot = {
            onAction(ScreenshotAction.NextScreenshot)
        },
        onPreviousScreenshot = {
            onAction(ScreenshotAction.PreviousScreenshot)
        },
        onUpdateSearchText = {
            onAction(ScreenshotAction.UpdateSearchText(it))
        },
        onSelectCommand = {
            onAction(ScreenshotAction.SelectScreenshotCommand(it))
        },
        onUpdateSortType = {
            onAction(ScreenshotAction.UpdateSortType(it))
        },
    )
}

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
private fun ScreenshotScreen(
    selectedFile: File?,
    splitterState: SplitPaneState,
    files: List<File>,
    canCapture: Boolean,
    isCapturing: Boolean,
    selectCommand: ScreenshotCommand,
    commands: List<ScreenshotCommand>,
    searchText: String,
    sortType: SortType,
    onOpenPreview: () -> Unit,
    onOpenDirectory: () -> Unit,
    onCopyScreenshot: () -> Unit,
    onDeleteScreenshot: () -> Unit,
    onDeleteSpecificScreenshot: (File) -> Unit,
    onSelectCommand: (ScreenshotCommand) -> Unit,
    onTakeScreenshot: (ScreenshotCommand) -> Unit,
    onSelectScreenshot: (File) -> Unit,
    onNextScreenshot: () -> Unit,
    onPreviousScreenshot: () -> Unit,
    onUpdateSearchText: (String) -> Unit,
    onUpdateSortType: (SortType) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        HorizontalSplitPane(
            splitPaneState = splitterState,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1.0f),
        ) {
            first(minSize = 350.dp) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ScreenshotHeader(
                        searchText = searchText,
                        sortType = sortType,
                        onUpdateSortType = onUpdateSortType,
                        onUpdateSearchText = onUpdateSearchText,
                        modifier = Modifier,
                    )

                    Divider(modifier = Modifier.height(1.dp).fillMaxWidth().defaultBorder())

                    val explorerRoot = remember(files) { buildExplorerRoot(files) }
                    val selectedExplorerFile =
                        remember(selectedFile, explorerRoot) {
                            val targetPath = selectedFile?.absolutePath ?: return@remember ExplorerFile.NONE
                            explorerRoot.findFileByUrl(targetPath) ?: ExplorerFile.NONE
                        }
                    val selectedExplorerDataFile = selectedExplorerFile.asDataFile
                    val fileFromExplorer =
                        selectedExplorerDataFile?.let { dataFile ->
                            files.firstOrNull { it.absolutePath == dataFile.url }
                        }
                    val scrollState = rememberScrollState()
                    val focusRequester = remember { FocusRequester() }

                    if (explorerRoot.list.isNotEmpty()) {
                        Box(
                            modifier =
                                Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .padding(4.dp)
                                    .focusRequester(focusRequester)
                                    .focusable()
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
                            ThemedContextMenuArea(
                                items = {
                                    fileFromExplorer?.let { target ->
                                        listOf(
                                            ContextMenuItem(
                                                label = Language.delete,
                                                onClick = { onDeleteSpecificScreenshot(target) },
                                            ),
                                        )
                                    } ?: emptyList()
                                },
                            ) {
                                Explorer(
                                    rootDirectory = explorerRoot,
                                    selectedFile = selectedExplorerFile,
                                    expandedDirs = emptyList(),
                                    onClickArrow = {},
                                    onClickFile = { file ->
                                        file.asDataFile?.let { dataFile ->
                                            files.firstOrNull { it.absolutePath == dataFile.url }
                                                ?.let(onSelectScreenshot)
                                        }
                                    },
                                    modifier =
                                        Modifier
                                            .fillMaxSize()
                                            .verticalScroll(scrollState),
                                )
                            }

                            VerticalScrollbar(
                                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight().width(8.dp),
                                adapter = rememberScrollbarAdapter(scrollState = scrollState),
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier.weight(1f).fillMaxWidth(),
                        ) {
                            Text(
                                text = Language.notFoundScreenshot,
                                modifier = Modifier.align(Alignment.Center),
                            )
                        }
                    }

                    ScreenshotMenu(
                        selectedCommand = selectCommand,
                        onSelectCommand = onSelectCommand,
                        commands = commands,
                        canCapture = canCapture,
                        isCapturing = isCapturing,
                        onTakeScreenshot = onTakeScreenshot,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp, vertical = 8.dp),
                    )
                }
            }

            second {
                Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
                    ScreenshotViewer(
                        file = selectedFile,
                        isCapturing = isCapturing,
                        onOpenPreview = onOpenPreview,
                        onCopyScreenshot = onCopyScreenshot,
                        onOpenDirectory = onOpenDirectory,
                        modifier =
                            Modifier
                                .weight(1.0f)
                                .fillMaxHeight(),
                    )

                }
            }

            splitter {
                visiblePart {
                    Box(
                        Modifier
                            .width(1.dp)
                            .fillMaxHeight()
                            .border(BorderStroke(1.dp, UserColor.getSplitterColor())),
                    )
                }

                handle {
                    Box(
                        Modifier
                            .markAsHandle()
                            .cursorForHorizontalResize()
                            .width(10.dp)
                            .fillMaxHeight()
                            .markAsHandle(),
                    )
                }
            }
        }
    }
}

private fun buildExplorerRoot(files: List<File>): ExplorerDirectory {
    val nodes =
        files.mapNotNull { file ->
            ExplorerDataFile(
                id = file.absolutePath,
                name = file.name,
                url = file.absolutePath,
            )
        }

    return ExplorerDirectory(
        id = "screenshot-root",
        name = "Screenshots",
        list = nodes,
    )
}

private fun ExplorerDirectory.findFileByUrl(url: String): ExplorerFile? {
    list.forEach { file ->
        when (file) {
            is ExplorerDataFile -> if (file.url == url) return file
            is ExplorerDirectory -> {
                val result = file.findFileByUrl(url)
                if (result != null) return result
            }
        }
    }
    return null
}

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
@Preview
private fun ScreenshotScreen_Preview() {
    ScreenshotScreen(
        selectedFile = null,
        splitterState = rememberSplitPaneState(),
        files = emptyList(),
        canCapture = true,
        isCapturing = false,
        selectCommand = ScreenshotCommand.Both,
        commands = emptyList(),
        searchText = "",
        sortType = SortType.SORT_BY_NAME_ASC,
        onOpenPreview = {},
        onOpenDirectory = {},
        onCopyScreenshot = {},
        onDeleteScreenshot = {},
        onDeleteSpecificScreenshot = { _ -> },
        onTakeScreenshot = {},
        onSelectScreenshot = { _ -> },
        onNextScreenshot = {},
        onPreviousScreenshot = {},
        onUpdateSearchText = {},
        onSelectCommand = {},
        onUpdateSortType = {},
    )
}
