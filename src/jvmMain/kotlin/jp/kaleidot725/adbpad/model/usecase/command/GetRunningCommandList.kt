package jp.kaleidot725.adbpad.model.usecase.command

import jp.kaleidot725.adbpad.model.data.Command

class GetRunningCommandList {
    operator fun invoke(runningCommands: List<Command>): List<Command> {
        return listOf(
            Command.LayoutBorderOn(runningCommands.any { it is Command.LayoutBorderOn }),
            Command.LayoutBorderOff(runningCommands.any { it is Command.LayoutBorderOff }),
            Command.TapEffectOn(runningCommands.any { it is Command.TapEffectOn }),
            Command.TapEffectOff(runningCommands.any { it is Command.TapEffectOff }),
            Command.SleepModeOff(runningCommands.any { it is Command.SleepModeOff }),
            Command.SleepModeOn(runningCommands.any { it is Command.SleepModeOn }),
            Command.DarkThemeOn(runningCommands.any { it is Command.DarkThemeOn }),
            Command.DarkThemeOff(runningCommands.any { it is Command.DarkThemeOff }),
            Command.WifiOn(runningCommands.any { it is Command.WifiOn }),
            Command.WifiOff(runningCommands.any { it is Command.WifiOff }),
            Command.DataOn(runningCommands.any { it is Command.DataOn }),
            Command.DataOff(runningCommands.any { it is Command.DataOff }),
            Command.WifiAndDataOn(runningCommands.any { it is Command.WifiAndDataOn }),
            Command.WifiAndDataOff(runningCommands.any { it is Command.WifiAndDataOff }),
        )
    }
}
