package jp.kaleidot725.adbpad

import jp.kaleidot725.adbpad.model.data.Command

data class MainState(
    val commands: List<Command> = emptyList(),
)
