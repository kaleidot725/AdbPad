package jp.kaleidot725.adbpad.core.utils

import java.awt.Image
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.ClipboardOwner
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import java.awt.datatransfer.UnsupportedFlavorException
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

object ClipBoardUtils {
    private val clipboard get() = Toolkit.getDefaultToolkit().systemClipboard

    fun copyImage(file: File): Boolean =
        try {
            val image = ImageIO.read(file) ?: return false
            val imageSelection = ImageSelection(image)
            clipboard.setContents(imageSelection, imageSelection)
            true
        } catch (e: Exception) {
            false
        }

    private class ImageSelection(
        private val image: BufferedImage,
    ) : Transferable, ClipboardOwner {
        override fun getTransferDataFlavors(): Array<DataFlavor> = arrayOf(DataFlavor.imageFlavor)

        override fun isDataFlavorSupported(flavor: DataFlavor): Boolean = DataFlavor.imageFlavor.equals(flavor)

        @Throws(UnsupportedFlavorException::class, IOException::class)
        override fun getTransferData(flavor: DataFlavor): Any {
            if (!isDataFlavorSupported(flavor)) {
                throw UnsupportedFlavorException(flavor)
            }
            return image
        }

        override fun lostOwnership(
            clipboard: Clipboard?,
            contents: Transferable?,
        ) {}
    }
}
