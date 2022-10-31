package jp.kaleidot725.adbpad.domain.model

import com.malinskiy.adam.request.shell.v1.ShellCommandRequest

interface Command {
    val title: String
    val details: String
    val isRunning: Boolean
    val requests: List<ShellCommandRequest>

    data class LayoutBorderOn(override val isRunning: Boolean = false) : Command {
        override val title: String = Language.COMMAND_LAYOUT_BORDER_ON_TITLE
        override val details: String = Language.COMMAND_LAYOUT_BORDER_ON_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("setprop debug.layout true"),
            ShellCommandRequest("service call activity 1599295570")
        )
    }

    data class LayoutBorderOff(override val isRunning: Boolean = false) : Command {
        override val title: String = Language.COMMAND_LAYOUT_BORDER_OFF_TITLE
        override val details: String = Language.COMMAND_LAYOUT_BORDER_OFF_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("setprop debug.layout false"),
            ShellCommandRequest("service call activity 1599295570")
        )
    }

    data class TapEffectOn(override val isRunning: Boolean = false) : Command {
        override val title: String = Language.COMMAND_TAP_EFFECT_ON_TITLE
        override val details: String = Language.COMMAND_TAP_EFFECT_ON_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("settings put system show_touches 1"),
        )
    }

    data class TapEffectOff(override val isRunning: Boolean = false) : Command {
        override val title: String = Language.COMMAND_TAP_EFFECT_OFF_TITLE
        override val details: String = Language.COMMAND_TAP_EFFECT_OFF_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("settings put system show_touches 0"),
        )
    }

    data class SleepModeOff(override val isRunning: Boolean = false) : Command {
        override val title: String = Language.COMMAND_SLEEP_MODE_OFF_TITLE
        override val details: String = Language.COMMAND_SLEEP_MODE_OFF_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("setting put global stay_on_while_plugged_in 7")
        )
    }

    data class SleepModeOn(override val isRunning: Boolean = false) : Command {
        override val title: String = Language.COMMAND_SLEEP_MODE_ON_TITLE
        override val details: String = Language.COMMAND_SLEEP_MODE_ON_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("setting put global stay_on_while_plugged_in ０")
        )
    }

    data class DarkThemeOn(override val isRunning: Boolean = false) : Command {
        override val title: String = Language.COMMAND_DARK_THEME_ON_TITLE
        override val details: String = Language.COMMAND_DARK_THEME_ON_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("cmd uimode night yes"))
    }

    data class DarkThemeOff(override val isRunning: Boolean = false) : Command {
        override val title: String = Language.COMMAND_DARK_THEME_OFF_TITLE
        override val details: String = Language.COMMAND_DARK_THEME_OFF_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("cmd uimode night no"))
    }

    data class WifiOn(override val isRunning: Boolean = false) : Command {
        override val title: String = Language.COMMAND_WIFI_ON_TITLE
        override val details: String = Language.COMMAND_WIFI_ON_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc wifi enable"))
    }

    data class WifiOff(override val isRunning: Boolean = false) : Command {
        override val title: String = Language.COMMAND_WIFI_OFF_TITLE
        override val details: String = Language.COMMAND_WIFI_OFF_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc wifi disable"))
    }

    data class DataOn(override val isRunning: Boolean = false) : Command {
        override val title: String = Language.COMMAND_DATA_ON_TITLE
        override val details: String = Language.COMMAND_DATA_ON_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc data enable"))
    }

    data class DataOff(override val isRunning: Boolean = false) : Command {
        override val title: String = Language.COMMAND_DATA_OFF_TITLE
        override val details: String = Language.COMMAND_DATA_OFF_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc data disable"))
    }

    data class InputText(
        private val text: String,
        override val isRunning: Boolean = false,
    ) : Command {
        override val title: String = Language.COMMAND_INPUT_TEXT_TITLE
        override val details: String = Language.COMMAND_INPUT_TEXT_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("input text $text"))
    }

    data class WifiAndDataOn(override val isRunning: Boolean = false) : Command {
        override val title: String = Language.COMMAND_WIFI_AND_DATA_ON_TITLE
        override val details: String = Language.COMMAND_WIFI_AND_DATA_ON_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("svc wifi enable"),
            ShellCommandRequest("svc data enable")
        )
    }

    data class WifiAndDataOff(override val isRunning: Boolean = false) : Command {
        override val title: String = Language.COMMAND_WIFI_AND_DATA_OFF_TITLE
        override val details: String = Language.COMMAND_WIFI_AND_DATA_OFF_DETAILS
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("svc wifi disable"),
            ShellCommandRequest("svc data disable")
        )
    }
}
