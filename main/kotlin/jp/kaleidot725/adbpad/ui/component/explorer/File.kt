package jp.kaleidot725.adbpad.ui.component.explorer

import java.util.UUID.randomUUID

sealed class File(
    open val id: String = randomUUID().toString(),
    open val name: String,
) {
    val isDirectory get() = this is Directory
    val asDirectory get() = this as? Directory
    val isDataFile get() = this is DataFile
    val asDataFile get() = this as? DataFile

    companion object {
        val NONE = Directory("", "", emptyList())
    }
}

data class Directory(
    override val id: String = randomUUID().toString(),
    override val name: String,
    val list: List<File>,
) : File(id, name)

data class DataFile(
    override val id: String = randomUUID().toString(),
    override val name: String,
    val url: String,
) : File(id, name)
