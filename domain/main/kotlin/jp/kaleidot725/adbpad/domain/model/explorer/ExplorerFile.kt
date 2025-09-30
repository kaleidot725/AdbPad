package jp.kaleidot725.adbpad.domain.model.explorer

import java.util.UUID.randomUUID

sealed class ExplorerFile(
    open val id: String = randomUUID().toString(),
    open val name: String,
) {
    val isDirectory get() = this is ExplorerDirectory
    val asDirectory get() = this as? ExplorerDirectory
    val isDataFile get() = this is ExplorerData
    val asDataFile get() = this as? ExplorerData

    companion object Companion {
        val NONE = ExplorerDirectory("", "", emptyList())
    }
}

data class ExplorerDirectory(
    override val id: String = randomUUID().toString(),
    override val name: String,
    val list: List<ExplorerFile>,
) : ExplorerFile(id, name)

data class ExplorerData(
    override val id: String = randomUUID().toString(),
    override val name: String,
    val url: String,
) : ExplorerFile(id, name)
