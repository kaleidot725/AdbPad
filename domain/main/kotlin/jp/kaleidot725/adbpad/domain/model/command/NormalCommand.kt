package jp.kaleidot725.adbpad.domain.model.command

import com.malinskiy.adam.request.shell.v1.ShellCommandRequest
import jp.kaleidot725.adbpad.domain.model.language.Language

interface NormalCommand {
    val title: String
    val details: String
    val isRunning: Boolean
    val requests: List<ShellCommandRequest>
    val category: NormalCommandCategory

    data class PointerLocationOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandPointerLocationOnTitle
        override val details: String get() = Language.commandPointerLocationOnDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("settings put system pointer_location 1"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class PointerLocationOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandPointerLocationOffTitle
        override val details: String get() = Language.commandPointerLocationOffDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("settings put system pointer_location 0"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class LayoutBorderOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandLayoutBorderOnTitle
        override val details: String get() = Language.commandLayoutBorderOnDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("setprop debug.layout true"),
                ShellCommandRequest("service call activity 1599295570"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class LayoutBorderOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandLayoutBorderOffTitle
        override val details: String get() = Language.commandLayoutBorderOffDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("setprop debug.layout false"),
                ShellCommandRequest("service call activity 1599295570"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class TapEffectOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandTapEffectOnTitle
        override val details: String get() = Language.commandTapEffectOnDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("settings put system show_touches 1"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class TapEffectOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandTapEffectOffTitle
        override val details: String get() = Language.commandTapEffectOffDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("settings put system show_touches 0"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class SleepModeOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandSleepModeOffTitle
        override val details: String get() = Language.commandSleepModeOffDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("settings put global stay_on_while_plugged_in 7"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class SleepModeOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandSleepModeOnTitle
        override val details: String get() = Language.commandSleepModeOnDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("settings put global stay_on_while_plugged_in 0"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class DarkThemeOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandDarkThemeOnTitle
        override val details: String get() = Language.commandDarkThemeOnDetails
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("cmd uimode night yes"))
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class DarkThemeOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandDarkThemeOffTitle
        override val details: String get() = Language.commandDarkThemeOffDetails
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("cmd uimode night no"))
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class WifiOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandWifiOnTitle
        override val details: String get() = Language.commandWifiOnDetails
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc wifi enable"))
        override val category: NormalCommandCategory = NormalCommandCategory.COM
    }

    data class WifiOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandWifiOffTitle
        override val details: String get() = Language.commandWifiOffDetails
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc wifi disable"))
        override val category: NormalCommandCategory = NormalCommandCategory.COM
    }

    data class DataOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandDataOnTitle
        override val details: String get() = Language.commandDataOnDetails
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc data enable"))
        override val category: NormalCommandCategory = NormalCommandCategory.COM
    }

    data class DataOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandDataOffTitle
        override val details: String get() = Language.commandDataOffDetails
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc data disable"))
        override val category: NormalCommandCategory = NormalCommandCategory.COM
    }

    data class WifiAndDataOn(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandWifiAndDataOnTitle
        override val details: String get() = Language.commandWifiAndDataOnDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("svc wifi enable"),
                ShellCommandRequest("svc data enable"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.COM
    }

    data class WifiAndDataOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandWifiAndDataOffTitle
        override val details: String get() = Language.commandWifiAndDataOffDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("svc wifi disable"),
                ShellCommandRequest("svc data disable"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.COM
    }

    data class ScreenPinningOff(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandScreenPinningOffTitle
        override val details: String get() = Language.commandScreenPinningOffDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("am task lock stop"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class EnableGestureNavigation(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandEnableGestureNavigationTitle
        override val details: String get() = Language.commandEnableGestureNavigationDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("cmd overlay enable com.android.internal.systemui.navbar.gestural"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class EnableTwoButtonNavigation(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandEnableTwoButtonNavigationTitle
        override val details: String get() = Language.commandEnableTwoButtonNavigationDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("cmd overlay enable com.android.internal.systemui.navbar.twobutton"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class EnableThreeButtonNavigation(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandEnableThreeButtonNavigationTitle
        override val details: String get() = Language.commandEnableThreeButtonNavigationDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("cmd overlay enable com.android.internal.systemui.navbar.threebutton"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class SetLanguageJapanese(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandSetLanguageJapaneseTitle
        override val details: String get() = Language.commandSetLanguageJapaneseDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("setprop persist.sys.locale ja-JP"),
                ShellCommandRequest("am broadcast -a android.intent.action.LOCALE_CHANGED"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class SetLanguageEnglish(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandSetLanguageEnglishTitle
        override val details: String get() = Language.commandSetLanguageEnglishDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("setprop persist.sys.locale en-US"),
                ShellCommandRequest("am broadcast -a android.intent.action.LOCALE_CHANGED"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class SetLanguageChinese(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandSetLanguageChineseTitle
        override val details: String get() = Language.commandSetLanguageChineseDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("setprop persist.sys.locale zh-CN"),
                ShellCommandRequest("am broadcast -a android.intent.action.LOCALE_CHANGED"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class SetLanguageKorean(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandSetLanguageKoreanTitle
        override val details: String get() = Language.commandSetLanguageKoreanDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("setprop persist.sys.locale ko-KR"),
                ShellCommandRequest("am broadcast -a android.intent.action.LOCALE_CHANGED"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class SetLanguageSpanish(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandSetLanguageSpanishTitle
        override val details: String get() = Language.commandSetLanguageSpanishDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("setprop persist.sys.locale es-ES"),
                ShellCommandRequest("am broadcast -a android.intent.action.LOCALE_CHANGED"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class SetLanguageFrench(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandSetLanguageFrenchTitle
        override val details: String get() = Language.commandSetLanguageFrenchDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("setprop persist.sys.locale fr-FR"),
                ShellCommandRequest("am broadcast -a android.intent.action.LOCALE_CHANGED"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }

    data class SetLanguageGerman(override val isRunning: Boolean = false) : NormalCommand {
        override val title: String get() = Language.commandSetLanguageGermanTitle
        override val details: String get() = Language.commandSetLanguageGermanDetails
        override val requests: List<ShellCommandRequest> =
            listOf(
                ShellCommandRequest("setprop persist.sys.locale de-DE"),
                ShellCommandRequest("am broadcast -a android.intent.action.LOCALE_CHANGED"),
            )
        override val category: NormalCommandCategory = NormalCommandCategory.UI
    }
}
