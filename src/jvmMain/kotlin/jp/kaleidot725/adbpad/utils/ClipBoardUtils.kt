package jp.kaleidot725.adbpad.utils

import java.awt.Image
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.ClipboardOwner
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import java.awt.datatransfer.UnsupportedFlavorException
import java.awt.image.BufferedImage
import java.io.IOException


object ClipBoardUtils {
    private val clipboard get() = Toolkit.getDefaultToolkit().systemClipboard
    private val owner get() = Owner()

    fun copyImage(image: BufferedImage) {
        val transferableImage = TransferableImage(image)
        clipboard.setContents(transferableImage, owner)
    }

    private class Owner : ClipboardOwner {
        override fun lostOwnership(arg0: Clipboard, arg1: Transferable) {
            println("Lost Clipboard Ownership")
        }
    }

    private class TransferableImage(val i: BufferedImage) : Transferable {
        @Throws(UnsupportedFlavorException::class, IOException::class)
        override fun getTransferData(flavor: DataFlavor): Any {
            return if (flavor.equals(DataFlavor.imageFlavor)) {
                i
            } else {
                throw UnsupportedFlavorException(flavor)
            }
        }

        override fun getTransferDataFlavors(): Array<DataFlavor> {
            return arrayOf(DataFlavor.imageFlavor)
        }

        override fun isDataFlavorSupported(flavor: DataFlavor): Boolean {
            val flavors = transferDataFlavors
            for (i in flavors.indices) {
                if (flavor.equals(flavors[i])) {
                    return true
                }
            }
            return false
        }
    }
}