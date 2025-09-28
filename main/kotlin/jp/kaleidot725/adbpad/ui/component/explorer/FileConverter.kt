package jp.kaleidot725.adbpad.ui.component.explorer

import java.io.File as JavaFile

fun JavaFile.toExplorerFile(): File {
    if (this.isDirectory) {
        val children = this.listFiles()?.map { it.toExplorerFile() } ?: emptyList()
        return Directory(id = this.path, name = this.name, list = children)
    } else {
        return DataFile(id = this.path, name = this.name, url = this.path)
    }
}
