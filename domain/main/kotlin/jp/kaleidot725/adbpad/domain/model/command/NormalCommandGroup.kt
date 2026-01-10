package jp.kaleidot725.adbpad.domain.model.command

data class NormalCommandGroup(
    val all: List<NormalCommand>,
    val ui: List<NormalCommand>,
    val communication: List<NormalCommand>,
    val favorite: List<NormalCommand> = emptyList(),
) {
    companion object {
        val Empty = NormalCommandGroup(emptyList(), emptyList(), emptyList(), emptyList())
    }
}
