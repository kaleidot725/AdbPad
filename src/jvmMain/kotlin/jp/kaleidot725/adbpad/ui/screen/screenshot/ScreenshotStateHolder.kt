package jp.kaleidot725.adbpad.ui.screen.screenshot

import jp.kaleidot725.adbpad.core.mvi.MVIBase
import jp.kaleidot725.adbpad.core.utils.ClipBoardUtils
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.model.os.OSContext
import jp.kaleidot725.adbpad.domain.model.screenshot.Screenshot
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
                ScreenshotAction.CopyScreenshotToClipboard -> copyScreenShotToClipboard()
                ScreenshotAction.DeleteScreenshotToClipboard -> deleteScreenShotToClipboard()
                is ScreenshotAction.SelectScreenshot -> selectScreenshot(uiAction.screenshot)
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
                previews = screenshots.filter { it.file?.name?.startsWith(searchText) ?: false },
            )
        }
    }

    private suspend fun updateSortType(sortType: SortType) {
        val screenshots = screenshotCommandRepository.getScreenshots(currentState.searchText, sortType)
        update {
            copy(
                searchText = searchText,
                sortType = sortType,
                previews = screenshots.filter { it.file?.name?.startsWith(searchText) ?: false },
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
                        preview = Screenshot.EMPTY,
                        isCapturing = true,
                    )
                }
            },
            onFailed = {
                val commands = getScreenshotCommandUseCase()
                update {
                    copy(
                        commands = commands,
                        preview = Screenshot.EMPTY,
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

    private fun copyScreenShotToClipboard() {
        val file = currentState.preview.file ?: return
        // 画像データとしてクリップボードにコピーを試行、失敗した場合はファイルとしてコピー
        if (!ClipBoardUtils.copyImage(file)) {
            ClipBoardUtils.copyFile(file)
        }
    }

    private suspend fun deleteScreenShotToClipboard() {
        screenshotCommandRepository.delete(currentState.preview)
        initPreviews()
    }

    private fun selectScreenshot(screenshot: Screenshot) {
        update {
            this.copy(preview = screenshot)
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
        val screenshot = screenshots.firstOrNull() ?: Screenshot(null)
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
