package jp.kaleidot725.adbpad.model.usecase

import jp.kaleidot725.adbpad.model.data.Command

class GetCommandListUseCase {
    operator fun invoke(): List<Command> {
        return listOf(
            Command.DarkThemeOn,
            Command.DarkThemeOff,
            Command.WifiAndDataOn,
            Command.WifiAndDataOff,
        )
    }
}
