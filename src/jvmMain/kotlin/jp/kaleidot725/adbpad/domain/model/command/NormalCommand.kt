package jp.kaleidot725.adbpad.domain.model.command

import com.malinskiy.adam.request.shell.v1.ShellCommandRequest
import jp.kaleidot725.adbpad.domain.model.language.Language

interface NormalCommand {
    val title: String
    val details: String
    val isRunning: Boolean
    val requests: List<ShellCommandRequest>

    data class LayoutBorderOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.COMMAND_LAYOUT_BORDER_ON_TITLE
        override val details: String get() = Language.COMMAND_LAYOUT_BORDER_ON_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("setprop debug.layout true"),
            ShellCommandRequest("service call activity 1599295570")
        )
    }

    data class LayoutBorderOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.COMMAND_LAYOUT_BORDER_OFF_TITLE
        override val details: String get() = Language.COMMAND_LAYOUT_BORDER_OFF_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("setprop debug.layout false"),
            ShellCommandRequest("service call activity 1599295570")
        )
    }

    data class TapEffectOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.COMMAND_TAP_EFFECT_ON_TITLE
        override val details: String get() = Language.COMMAND_TAP_EFFECT_ON_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("settings put system show_touches 1"),
        )
    }

    data class TapEffectOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.COMMAND_TAP_EFFECT_OFF_TITLE
        override val details: String get() = Language.COMMAND_TAP_EFFECT_OFF_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("settings put system show_touches 0"),
        )
    }

    data class SleepModeOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.COMMAND_SLEEP_MODE_OFF_TITLE
        override val details: String get() = Language.COMMAND_SLEEP_MODE_OFF_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("settings put global stay_on_while_plugged_in 7")
        )
    }

    data class SleepModeOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.COMMAND_SLEEP_MODE_ON_TITLE
        override val details: String get() = Language.COMMAND_SLEEP_MODE_ON_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("settings put global stay_on_while_plugged_in 0")
        )
    }

    data class DarkThemeOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.COMMAND_DARK_THEME_ON_TITLE
        override val details: String get() = Language.COMMAND_DARK_THEME_ON_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("cmd uimode night yes"))
    }

    data class DarkThemeOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.COMMAND_DARK_THEME_OFF_TITLE
        override val details: String get() = Language.COMMAND_DARK_THEME_OFF_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("cmd uimode night no"))
    }

    data class WifiOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.COMMAND_WIFI_ON_TITLE
        override val details: String get() = Language.COMMAND_WIFI_ON_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc wifi enable"))
    }

    data class WifiOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.COMMAND_WIFI_OFF_TITLE
        override val details: String get() = Language.COMMAND_WIFI_OFF_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc wifi disable"))
    }

    data class DataOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.COMMAND_DATA_ON_TITLE
        override val details: String get() = Language.COMMAND_DATA_ON_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc data enable"))
    }

    data class DataOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.COMMAND_DATA_OFF_TITLE
        override val details: String get() = Language.COMMAND_DATA_OFF_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc data disable"))
    }

    data class WifiAndDataOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.COMMAND_WIFI_AND_DATA_ON_TITLE
        override val details: String get() = Language.COMMAND_WIFI_AND_DATA_ON_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("svc wifi enable"),
            ShellCommandRequest("svc data enable")
        )
    }

    data class WifiAndDataOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.COMMAND_WIFI_AND_DATA_OFF_TITLE
        override val details: String get() = Language.COMMAND_WIFI_AND_DATA_OFF_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("svc wifi disable"),
            ShellCommandRequest("svc data disable")
        )
    }
}
