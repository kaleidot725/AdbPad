package jp.kaleidot725.adbpad.domain.repository

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.request.framebuffer.RawImageScreenCaptureAdapter
import com.malinskiy.adam.request.framebuffer.ScreenCaptureRequest
import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.model.command.ScreenshotCommand
import jp.kaleidot725.adbpad.domain.model.device.Device
import jp.kaleidot725.adbpad.domain.model.explorer.ExplorerData
import jp.kaleidot725.adbpad.domain.model.explorer.ExplorerDirectory
import jp.kaleidot725.adbpad.domain.model.explorer.ExplorerFile
import jp.kaleidot725.adbpad.domain.model.os.OSContext
import jp.kaleidot725.adbpad.domain.model.sort.SortType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.util.Date
import javax.imageio.ImageIO
import kotlin.math.max

class ScreenshotCommandRepositoryImpl : ScreenshotCommandRepository {
    private val adbClient = AndroidDebugBridgeClientFactory().build()

    init {
        ensureRootDirectory()
    }

    override fun getCommands(): List<ScreenshotCommand> =
        listOf(
            ScreenshotCommand.Both,
            ScreenshotCommand.Current,
            ScreenshotCommand.Light,
            ScreenshotCommand.Dark,
        )

    override suspend fun captureScreenshot(
        device: Device,
        command: ScreenshotCommand,
        onStart: suspend () -> Unit,
        onComplete: suspend (File) -> Unit,
        onFailed: suspend () -> Unit,
    ) {
        val date = Date()
        withContext(Dispatchers.IO) {
            ensureRootDirectory()
            onStart()

            val result =
                when (command) {
                    is ScreenshotCommand.Both -> sendBothCommand(device, date)
                    is ScreenshotCommand.Light -> sendLightCommand(device, date)
                    is ScreenshotCommand.Dark -> sendDarkCommand(device, date)
                    is ScreenshotCommand.Current -> sendCurrentCommand(device, date)
                    else -> false
                }

            delay(300)

            if (result) onComplete(resultFile(date.time)) else onFailed()
        }
    }

    override suspend fun getScreenshots(
        searchText: String,
        sortType: SortType,
    ): List<File> =
        withContext(Dispatchers.IO) {
            ensureRootDirectory()
            val files = collectScreenshotFiles(rootDirectory())
            val filteredFiles =
                files
                    .filter { file -> file.name.startsWith(searchText) }
            when (sortType) {
                SortType.SORT_BY_NAME_ASC -> filteredFiles.sortedBy { file -> file.name.lowercase() }
                SortType.SORT_BY_NAME_DESC -> filteredFiles.sortedByDescending { file -> file.name.lowercase() }
            }
        }

    override fun getDirectory(): ExplorerDirectory {
        ensureRootDirectory()
        val root = rootDirectory()
        val children = root.buildExplorerChildren()
        return ExplorerDirectory(
            id = root.absolutePath,
            name = ROOT_DIRECTORY_NAME,
            list = children,
        )
    }

    override suspend fun delete(file: File) {
        withContext(Dispatchers.IO) {
            file.delete()
        }
    }

    private suspend fun capture(
        device: Device,
        file: File,
        captureDelay: Long = DEFAULT_DELAY,
    ): Boolean {
        delay(captureDelay)
        ensureParentExists(file)
        val adb = AndroidDebugBridgeClientFactory().build()
        val adapter = RawImageScreenCaptureAdapter()
        val image = adb.execute(request = ScreenCaptureRequest(adapter), serial = device.serial).toBufferedImage()
        return ImageIO.write(image, EXTENSION_NAME, file)
    }

    private fun concat(
        fileA: File,
        fileB: File,
        outputFile: File,
    ): Boolean =
        try {
            ensureParentExists(outputFile)
            val inputA = ImageIO.read(fileA)
            val inputB = ImageIO.read(fileB)
            val totalWidth = inputA.width + inputB.width
            val maxHeight = max(inputA.height, inputB.height)
            val output = BufferedImage(totalWidth, maxHeight, BufferedImage.TYPE_INT_ARGB)

            output.graphics.drawImage(inputA, 0, 0, null)
            output.graphics.drawImage(inputB, inputA.width, 0, null)
            ImageIO.write(output, EXTENSION_NAME, outputFile)

            fileA.delete()
            fileB.delete()

            true
        } catch (e: IOException) {
            fileA.delete()
            fileB.delete()
            false
        }

    private suspend fun sendCommand(
        device: Device,
        command: NormalCommand,
    ): Boolean {
        return withContext(Dispatchers.IO) {
            command.requests.forEach { request ->
                val result = adbClient.execute(request, device.serial)
                if (result.exitCode != 0) {
                    return@withContext false
                }
            }
            return@withContext true
        }
    }

    companion object {
        private const val FILE_NAME_A = "screenshotA.png"
        private const val FILE_NAME_B = "screenshotB.png"
        private const val FILE_NAME_RESULT = "Screenshot"
        private const val EXTENSION_NAME = "png"
        private const val DEFAULT_DELAY = 500L
        private const val ROOT_DIRECTORY_NAME = "Screenshots"
    }

    private fun ensureRootDirectory() {
        val directory = rootDirectory()
        if (!directory.exists()) {
            directory.mkdirs()
        }
    }

    private fun rootDirectory(): File {
        val osContext = OSContext.resolveOSContext()
        return File(osContext.screenshotDirectory)
    }

    private fun tempFileA(): File = File(rootDirectory(), FILE_NAME_A)

    private fun tempFileB(): File = File(rootDirectory(), FILE_NAME_B)

    private fun resultFile(time: Long): File = File(rootDirectory(), "${FILE_NAME_RESULT}_$time.$EXTENSION_NAME")

    private fun collectScreenshotFiles(directory: File): List<File> {
        if (!directory.exists()) return emptyList()

        val result = mutableListOf<File>()
        val stack = ArrayDeque<File>()
        stack.add(directory)

        while (stack.isNotEmpty()) {
            val current = stack.removeLast()
            val children = current.listFiles()?.sortedBy { it.name.lowercase() } ?: continue
            children.forEach { child ->
                if (child.isDirectory) {
                    stack.add(child)
                } else if (child.isFile && child.extension.equals(EXTENSION_NAME, ignoreCase = true)) {
                    result += child
                }
            }
        }

        return result
    }

    private fun File.buildExplorerChildren(): List<ExplorerFile> {
        val children = listFiles()?.sortedBy { it.name.lowercase() } ?: emptyList()
        return children.mapNotNull { child ->
            when {
                child.isDirectory -> {
                    val directoryChildren = child.buildExplorerChildren()
                    if (directoryChildren.isEmpty()) {
                        null
                    } else {
                        ExplorerDirectory(
                            id = child.absolutePath,
                            name = child.name,
                            list = directoryChildren,
                        )
                    }
                }

                child.isFile && child.extension.equals(EXTENSION_NAME, ignoreCase = true) ->
                    ExplorerData(
                        id = child.absolutePath,
                        name = child.name,
                        url = child.absolutePath,
                    )

                else -> null
            }
        }
    }

    private fun ensureParentExists(file: File) {
        file.parentFile?.let { parent ->
            if (!parent.exists()) {
                parent.mkdirs()
            }
        }
    }

    private suspend fun sendBothCommand(
        device: Device,
        date: Date,
    ): Boolean {
        if (!sendCommand(device, NormalCommand.DarkThemeOn())) {
            return false
        }

        val firstCapture = tempFileA()
        if (!capture(device, firstCapture)) {
            return false
        }

        if (!sendCommand(device, NormalCommand.DarkThemeOff())) {
            return false
        }

        val secondCapture = tempFileB()
        if (!capture(device, secondCapture)) {
            return false
        }

        return concat(firstCapture, secondCapture, resultFile(date.time))
    }

    private suspend fun sendDarkCommand(
        device: Device,
        date: Date,
    ): Boolean {
        if (!sendCommand(device, NormalCommand.DarkThemeOn())) {
            return false
        }

        return capture(device, resultFile(date.time))
    }

    private suspend fun sendLightCommand(
        device: Device,
        date: Date,
    ): Boolean {
        if (!sendCommand(device, NormalCommand.DarkThemeOff())) {
            return false
        }

        return capture(device, resultFile(date.time))
    }

    private suspend fun sendCurrentCommand(
        device: Device,
        date: Date,
    ): Boolean = capture(device, resultFile(date.time))
}
