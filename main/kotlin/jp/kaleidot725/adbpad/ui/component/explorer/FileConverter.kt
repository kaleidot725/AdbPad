package jp.kaleidot725.adbpad.ui.component.explorer

import java.io.File as JavaFile

fun JavaFile.toExplorerFile(): File {
    if (this.isDirectory) {
        val children = this.listFiles()?.map { it.toExplorerFile() } ?: emptyList()
        return Directory(name = this.name, list = children)
    } else {
        return DataFile(name = this.name, url = this.path)
    }
}
