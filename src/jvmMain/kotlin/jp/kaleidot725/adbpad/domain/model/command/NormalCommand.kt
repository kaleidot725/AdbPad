package jp.kaleidot725.adbpad.domain.model.command

import com.malinskiy.adam.request.shell.v1.ShellCommandRequest
import jp.kaleidot725.adbpad.domain.model.language.Language

interface NormalCommand {
    val title: String
    val details: String
    val isRunning: Boolean
    val requests: List<ShellCommandRequest>

    data class LayoutBorderOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandLayoutBorderOnTitle
        override val details: String get() = Language.commandLayoutBorderOnDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("setprop debug.layout true"),
                ShellCommandRequest("service call activity 1599295570"),
            )
    }

    data class LayoutBorderOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandLayoutBorderOffTitle
        override val details: String get() = Language.commandLayoutBorderOffDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("setprop debug.layout false"),
                ShellCommandRequest("service call activity 1599295570"),
            )
    }

    data class TapEffectOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandTapEffectOnTitle
        override val details: String get() = Language.commandTapEffectOnDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("settings put system show_touches 1"),
            )
    }

    data class TapEffectOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandTapEffectOffTitle
        override val details: String get() = Language.commandTapEffectOffDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("settings put system show_touches 0"),
            )
    }

    data class SleepModeOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandSleepModeOffTitle
        override val details: String get() = Language.commandSleepModeOffDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("settings put global stay_on_while_plugged_in 7"),
            )
    }

    data class SleepModeOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandSleepModeOnTitle
        override val details: String get() = Language.commandSleepModeOnDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("settings put global stay_on_while_plugged_in 0"),
            )
    }

    data class DarkThemeOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandDarkThemeOnTitle
        override val details: String get() = Language.commandDarkThemeOnDetails
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("cmd uimode night yes"))
    }

    data class DarkThemeOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandDarkThemeOffTitle
        override val details: String get() = Language.commandDarkThemeOffDetails
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("cmd uimode night no"))
    }

    data class WifiOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandWifiOnTitle
        override val details: String get() = Language.commandWifiOnDetails
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc wifi enable"))
    }

    data class WifiOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandWifiOffTitle
        override val details: String get() = Language.commandWifiOffDetails
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc wifi disable"))
    }

    data class DataOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandDataOnTitle
        override val details: String get() = Language.commandDataOnDetails
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc data enable"))
    }

    data class DataOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandDataOffTitle
        override val details: String get() = Language.commandDataOffDetails
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc data disable"))
    }

    data class WifiAndDataOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandWifiAndDataOnTitle
        override val details: String get() = Language.commandWifiAndDataOnDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("svc wifi enable"),
                ShellCommandRequest("svc data enable"),
            )
    }

    data class WifiAndDataOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandWifiAndDataOffTitle
        override val details: String get() = Language.commandWifiAndDataOffDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("svc wifi disable"),
                ShellCommandRequest("svc data disable"),
            )
    }

    data class ScreenPinningOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandScreenPinningOffTitle
        override val details: String get() = Language.commandScreenPinningOffDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("am task lock stop"),
            )
    }
}
