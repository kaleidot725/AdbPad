package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.explorer.ExplorerDirectory
import jp.kaleidot725.adbpad.domain.model.sort.SortType
import java.io.File

interface ScreenshotCommandRepository {
    fun getCommands(): List<ScreenshotCommand>

    suspend fun captureScreenshot(
        device: Device,
        command: ScreenshotCommand,
        onStart: suspend () -> Unit,
        onComplete: suspend (File) -> Unit,
        onFailed: suspend () -> Unit,
    )

    suspend fun getScreenshots(
        searchText: String,
        sortType: SortType,
    ): List<File>

    fun getDirectory(): ExplorerDirectory

    suspend fun delete(file: File)
}
