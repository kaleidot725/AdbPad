package jp.kaleidot725.adbpad.utils

import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.ClipboardOwner
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import java.awt.datatransfer.UnsupportedFlavorException
import java.io.File
import java.io.IOException

object ClipBoardUtils {
    private val clipboard get() = Toolkit.getDefaultToolkit().systemClipboard

    fun copyFile(file: File): Boolean {
        return try {
            val fileSelection = FileSelection(file)
            clipboard.setContents(fileSelection, fileSelection)
            true
        } catch (e: IllegalStateException) {
            false
        }
    }

    private class FileSelection(private val file: File) : Transferable, ClipboardOwner {
        override fun getTransferDataFlavors(): Array<DataFlavor> {
            return arrayOf(DataFlavor.javaFileListFlavor)
        }

        override fun isDataFlavorSupported(flavor: DataFlavor): Boolean {
            return DataFlavor.javaFileListFlavor.equals(flavor)
        }

        @Throws(UnsupportedFlavorException::class, IOException::class)
        override fun getTransferData(flavor: DataFlavor): Any {
            return listOf(file)
        }

        override fun lostOwnership(
            clipboard: Clipboard?,
            contents: Transferable?,
        ) {}
    }
}
