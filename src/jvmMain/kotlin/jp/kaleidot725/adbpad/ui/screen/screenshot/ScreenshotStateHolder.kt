package jp.kaleidot725.adbpad.ui.screen.screenshot

import jp.kaleidot725.adbpad.core.mvi.MVI
import jp.kaleidot725.adbpad.core.mvi.mvi
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.model.os.OSContext
import jp.kaleidot725.adbpad.domain.model.screenshot.Screenshot
import jp.kaleidot725.adbpad.domain.repository.ScreenshotCommandRepository
import jp.kaleidot725.adbpad.domain.usecase.device.GetSelectedDeviceFlowUseCase
import jp.kaleidot725.adbpad.domain.usecase.screenshot.GetScreenshotCommandUseCase
import jp.kaleidot725.adbpad.domain.usecase.screenshot.TakeScreenshotUseCase
import jp.kaleidot725.adbpad.domain.utils.ClipBoardUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
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
) : MVI<ScreenshotState, ScreenshotAction, ScreenshotSideEffect> by mvi(initialUiState = ScreenshotState()) {
    override fun onSetup() {
        coroutineScope.launch {
            val commands = getScreenshotCommandUseCase()
            update { copy(commands = commands) }

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
            update { copy(commands = commands) }

            initPreviews()
        }
    }

    override fun onDispose() {
        coroutineScope.cancel()
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
            }
        }
    }

    private suspend fun updateSearchText(searchText: String) {
        val screenshots = screenshotCommandRepository.getScreenshots()
        update {
            copy(
                searchText = searchText,
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
                val screenshots = screenshotCommandRepository.getScreenshots()
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
        ClipBoardUtils.copyFile(file)
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
        val screenshots = screenshotCommandRepository.getScreenshots()
        val screenshot = screenshots.first()
        update {
            this.copy(
                previews = screenshots,
                preview = screenshot,
            )
        }
    }
}
