package jp.kaleidot725.adbpad.model.usecase.command

import jp.kaleidot725.adbpad.model.data.Command

class GetCommandListUseCase {
    operator fun invoke(): List<Command> {
        return listOf(
            Command.LayoutBorderOn,
            Command.LayoutBorderOff,
            Command.TapEffectOn,
            Command.TapEffectOff,
            Command.SleepModeOff,
            Command.SleepModeOn,
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