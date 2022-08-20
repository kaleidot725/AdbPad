package jp.kaleidot725.adbpad.model.usecase

import jp.kaleidot725.adbpad.model.data.Command

class GetCommandListUseCase {
    operator fun invoke(): List<Command> {
        return listOf(
            Command.DarkThemeOn,
            Command.DarkThemeOff,
            Command.WifiOn,
            Command.WifiOff,
            Command.DataOn,
            Command.DataOff,
            Command.WifiAndDataOn,
            Command.WifiAndDataOff,
        )
    }
}