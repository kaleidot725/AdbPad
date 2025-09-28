package jp.kaleidot725.adbpad.ui.screen.screenshot

import jp.kaleidot725.adbpad.core.mvi.MVIBase
import jp.kaleidot725.adbpad.core.utils.ClipBoardUtils
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.model.os.OSContext
import jp.kaleidot725.adbpad.domain.model.sort.SortType
import jp.kaleidot725.adbpad.domain.repository.ScreenshotCommandRepository
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.screenshot.GetScreenshotCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.screenshot.TakeScreenshotUseCase
import jp.kaleidot725.adbpad.ui.screen.screenshot.state.ScreenshotAction
import jp.kaleidot725.adbpad.ui.screen.screenshot.state.ScreenshotSideEffect
import jp.kaleidot725.adbpad.ui.screen.screenshot.state.ScreenshotState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.awt.Desktop
import java.io.File

class ScreenshotStateHolder(
    private val takeScreenshotUseCase: TakeScreenshotUseCase,
    private val getScreenshotCommandUseCase: GetScreenshotCommandUseCase,
    private val getSelectedDeviceFlowUseCase: GetSelectedDeviceFlowUseCase,
    private val screenshotCommandRepository: ScreenshotCommandRepository,
) : MVIBase<ScreenshotState, ScreenshotAction, ScreenshotSideEffect>(initialUiState = ScreenshotState()) {
    override fun onSetup() {
        coroutineScope.launch {
            val commands = getScreenshotCommandUseCase()
            update {
                copy(
                    selectedCommand = commands.first(),
                    commands = commands,
                )
            }

            initPreviews()
        }

        coroutineScope.launch {
            getSelectedDeviceFlowUseCase().collectLatest {
                update { copy(selectedDevice = it) }
            }
        }
    }

    override fun onRefresh() {
        coroutineScope.launch {
            val commands = getScreenshotCommandUseCase()
            update {
                copy(
                    selectedCommand = commands.first(),
                    commands = commands,
                )
            }

            initPreviews()
        }
    }

    override fun onAction(uiAction: ScreenshotAction) {
        coroutineScope.launch {
            when (uiAction) {
                is ScreenshotAction.TakeScreenshot -> takeScreenShot(uiAction.command)
                ScreenshotAction.OpenDirectory -> openDirectory()
                ScreenshotAction.OpenPreview -> openPreview()
                ScreenshotAction.CopyScreenshotToClipboard -> copyScreenShotToClipboard()
                ScreenshotAction.DeleteScreenshotToClipboard -> deleteScreenShotToClipboard()
                is ScreenshotAction.DeleteScreenshot -> deleteSpecificScreenshot(uiAction.file)
                is ScreenshotAction.SelectScreenshot -> selectScreenshot(uiAction.file)
                ScreenshotAction.NextScreenshot -> nextScreenshot()
                ScreenshotAction.PreviousScreenshot -> previousScreenshot()
                is ScreenshotAction.UpdateSearchText -> updateSearchText(uiAction.text)
                is ScreenshotAction.SelectScreenshotCommand -> selectScreenshotCommand(uiAction.command)
                is ScreenshotAction.UpdateSortType -> updateSortType(uiAction.sortType)
            }
        }
    }

    private suspend fun updateSearchText(searchText: String) {
        val screenshots = screenshotCommandRepository.getScreenshots(searchText, currentState.sortType)
        update {
            copy(
                searchText = searchText,
                previews = screenshots.filter { it.name.startsWith(searchText) },
            )
        }
    }

    private suspend fun updateSortType(sortType: SortType) {
        val screenshots = screenshotCommandRepository.getScreenshots(currentState.searchText, sortType)
        update {
            copy(
                searchText = searchText,
                sortType = sortType,
                previews = screenshots.filter { it.name.startsWith(searchText) },
            )
        }
    }

    private suspend fun takeScreenShot(command: ScreenshotCommand) {
        val selectedDevice = state.value.selectedDevice ?: return
        takeScreenshotUseCase(
            device = selectedDevice,
            command = command,
            onStart = {
                val commands = getScreenshotCommandUseCase()
                update {
                    copy(
                        commands = commands,
                        preview = null,
                        isCapturing = true,
                    )
                }
            },
            onFailed = {
                val commands = getScreenshotCommandUseCase()
                update {
                    copy(
                        commands = commands,
                        preview = null,
                        isCapturing = false,
                    )
                }
            },
            onComplete = {
                val commands = getScreenshotCommandUseCase()
                val screenshots =
                    screenshotCommandRepository.getScreenshots(
                        currentState.searchText,
                        currentState.sortType,
                    )
                update {
                    copy(
                        commands = commands,
                        preview = it,
                        previews = screenshots,
                        isCapturing = false,
                    )
                }
            },
        )
    }

    private suspend fun openDirectory() {
        val file = File(OSContext.resolveOSContext().screenshotDirectory)
        withContext(Dispatchers.IO) { Desktop.getDesktop().open(file) }
    }

    private suspend fun openPreview() {
        val file = currentState.preview ?: return
        withContext(Dispatchers.IO) {
            runCatching { Desktop.getDesktop().open(file) }
        }
    }

    private fun copyScreenShotToClipboard() {
        val file = currentState.preview ?: return
        ClipBoardUtils.copyFile(file)
    }

    private suspend fun deleteScreenShotToClipboard() {
        val preview = currentState.preview ?: return
        screenshotCommandRepository.delete(preview)
        initPreviews()
    }

    private suspend fun deleteSpecificScreenshot(file: File) {
        screenshotCommandRepository.delete(file)
        val screenshots =
            screenshotCommandRepository.getScreenshots(
                currentState.searchText,
                currentState.sortType,
            )

        if (screenshots.isEmpty()) {
            update {
                copy(
                    previews = screenshots,
                    preview = null,
                )
            }
        } else {
            val wasSelectedScreenshotDeleted = currentState.preview == file
            if (wasSelectedScreenshotDeleted) {
                val newSelectedScreenshot = screenshots.firstOrNull()
                update {
                    copy(
                        previews = screenshots,
                        preview = newSelectedScreenshot,
                    )
                }
            } else {
                update {
                    copy(
                        previews = screenshots,
                    )
                }
            }
        }
    }

    private fun selectScreenshot(file: File) {
        update {
            this.copy(preview = file)
        }
    }

    private fun nextScreenshot() {
        val nextIndex = currentState.previews.indexOf(currentState.preview) + 1
        val nextPreview = currentState.previews.getOrNull(nextIndex) ?: return
        update {
            this.copy(preview = nextPreview)
        }
    }

    private fun previousScreenshot() {
        val previousIndex = currentState.previews.indexOf(currentState.preview) - 1
        val previousPreview = currentState.previews.getOrNull(previousIndex) ?: return
        update {
            this.copy(preview = previousPreview)
        }
    }

    private suspend fun initPreviews() {
        val screenshots =
            screenshotCommandRepository.getScreenshots(
                currentState.searchText,
                currentState.sortType,
            )
        val screenshot = screenshots.firstOrNull()
        update {
            this.copy(
                previews = screenshots,
                preview = screenshot,
            )
        }
    }

    private fun selectScreenshotCommand(command: ScreenshotCommand) {
        update {
            this.copy(
                selectedCommand = command,
            )
        }
    }
}
