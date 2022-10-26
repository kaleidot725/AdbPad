package jp.kaleidot725.adbpad.model.usecase.command

import jp.kaleidot725.adbpad.model.data.Command

class GetNotRunningCommandList {
    operator fun invoke(): List<Command> {
        return listOf(
            Command.LayoutBorderOn(false),
            Command.LayoutBorderOff(false),
            Command.TapEffectOn(false),
            Command.TapEffectOff(false),
            Command.SleepModeOff(false),
            Command.SleepModeOn(false),
            Command.DarkThemeOn(false),
            Command.DarkThemeOff(false),
            Command.WifiOn(false),
            Command.WifiOff(false),
            Command.DataOn(false),
            Command.DataOff(false),
            Command.WifiAndDataOn(false),
            Command.WifiAndDataOff(false),
        )
    }
}
