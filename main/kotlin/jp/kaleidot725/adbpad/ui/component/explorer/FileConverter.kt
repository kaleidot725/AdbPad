package jp.kaleidot725.adbpad.ui.component.explorer

import jp.kaleidot725.adbpad.domain.model.explorer.ExplorerData
import jp.kaleidot725.adbpad.domain.model.explorer.ExplorerDirectory
import jp.kaleidot725.adbpad.domain.model.explorer.ExplorerFile
import java.io.File as JavaFile

fun JavaFile.toExplorerFile(): ExplorerFile {
    if (this.isDirectory) {
        val children = this.listFiles()?.map { it.toExplorerFile() } ?: emptyList()
        return ExplorerDirectory(id = this.path, name = this.name, list = children)
    } else {
        return ExplorerData(id = this.path, name = this.name, url = this.path)
    }
}
