package jp.kaleidot725.adbpad.domain.usecase.command

import jp.kaleidot725.adbpad.domain.model.Command

class GetNotRunningCommandList {
    operator fun invoke(): List<Command> = listOf(
        Command.LayoutBorderOn(),
        Command.LayoutBorderOff(),
        Command.TapEffectOn(),
        Command.TapEffectOff(),
        Command.SleepModeOff(),
        Command.SleepModeOn(),
        Command.DarkThemeOn(),
        Command.DarkThemeOff(),
        Command.WifiOn(),
        Command.WifiOff(),
        Command.DataOn(),
        Command.DataOff(),
        Command.WifiAndDataOn(),
        Command.WifiAndDataOff()
    )
}
