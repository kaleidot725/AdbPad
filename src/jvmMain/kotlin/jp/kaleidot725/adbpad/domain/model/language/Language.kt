package jp.kaleidot725.adbpad.domain.model.language

import jp.kaleidot725.adbpad.domain.model.language.resources.ChineseResources
import jp.kaleidot725.adbpad.domain.model.language.resources.EnglishResources
import jp.kaleidot725.adbpad.domain.model.language.resources.JapaneseResources
import jp.kaleidot725.adbpad.domain.model.language.resources.StringResources
import jp.kaleidot725.adbpad.domain.model.language.resources.TurkishResources

object Language : StringResources {
    override val windowTitle: String
        get() = getCurrentResources().windowTitle
    override val notFoundDevice: String
        get() = getCurrentResources().notFoundDevice
    override val notFoundCommand: String
        get() = getCurrentResources().notFoundCommand
    override val notFoundInputText: String
        get() = getCurrentResources().notFoundInputText
    override val notFoundScreenshot: String
        get() = getCurrentResources().notFoundScreenshot
    override val execute: String
        get() = getCurrentResources().execute
    override val tab: String
        get() = getCurrentResources().tab
    override val save: String
        get() = getCurrentResources().save
    override val delete: String
        get() = getCurrentResources().delete
    override val send: String
        get() = getCurrentResources().send
    override val cancel: String
        get() = getCurrentResources().cancel
    override val targetDevice: String
        get() = getCurrentResources().targetDevice
    override val tool: String
        get() = getCurrentResources().tool
    override val setting: String
        get() = getCurrentResources().setting
    override val dark: String
        get() = getCurrentResources().dark
    override val light: String
        get() = getCurrentResources().light
    override val system: String
        get() = getCurrentResources().system
    override val search: String
        get() = getCurrentResources().search
    override val textCommandUnTitle: String
        get() = getCurrentResources().textCommandUnTitle
    override val screenshotTakeByCurrentTheme: String
        get() = getCurrentResources().screenshotTakeByCurrentTheme
    override val screenshotTakeByDarkTheme: String
        get() = getCurrentResources().screenshotTakeByDarkTheme
    override val screenshotTakeByLightTheme: String
        get() = getCurrentResources().screenshotTakeByLightTheme
    override val screenshotTakeByBothTheme: String
        get() = getCurrentResources().screenshotTakeByBothTheme
    override val commandStartEventFormat: String
        get() = getCurrentResources().commandStartEventFormat
    override val commandEndEventFormat: String
        get() = getCurrentResources().commandEndEventFormat
    override val commandErrorEventFormat: String
        get() = getCurrentResources().commandErrorEventFormat

    override val commandPointerLocationOnTitle: String
        get() = getCurrentResources().commandPointerLocationOnTitle
    override val commandPointerLocationOnDetails: String
        get() = getCurrentResources().commandPointerLocationOnDetails
    override val commandPointerLocationOffTitle: String
        get() = getCurrentResources().commandPointerLocationOffTitle
    override val commandPointerLocationOffDetails: String
        get() = getCurrentResources().commandPointerLocationOffDetails

    override val commandLayoutBorderOnTitle: String
        get() = getCurrentResources().commandLayoutBorderOnTitle
    override val commandLayoutBorderOnDetails: String
        get() = getCurrentResources().commandLayoutBorderOnDetails
    override val commandLayoutBorderOffTitle: String
        get() = getCurrentResources().commandLayoutBorderOffTitle
    override val commandLayoutBorderOffDetails: String
        get() = getCurrentResources().commandLayoutBorderOffDetails
    override val commandTapEffectOnTitle: String
        get() = getCurrentResources().commandTapEffectOnTitle
    override val commandTapEffectOnDetails: String
        get() = getCurrentResources().commandTapEffectOnDetails
    override val commandTapEffectOffTitle: String
        get() = getCurrentResources().commandTapEffectOffTitle
    override val commandTapEffectOffDetails: String
        get() = getCurrentResources().commandTapEffectOffDetails
    override val commandSleepModeOnTitle: String
        get() = getCurrentResources().commandSleepModeOnTitle
    override val commandSleepModeOnDetails: String
        get() = getCurrentResources().commandSleepModeOnDetails
    override val commandSleepModeOffTitle: String
        get() = getCurrentResources().commandSleepModeOffTitle
    override val commandSleepModeOffDetails: String
        get() = getCurrentResources().commandSleepModeOffDetails
    override val commandDarkThemeOnTitle: String
        get() = getCurrentResources().commandDarkThemeOnTitle
    override val commandDarkThemeOnDetails: String
        get() = getCurrentResources().commandDarkThemeOnDetails
    override val commandDarkThemeOffTitle: String
        get() = getCurrentResources().commandDarkThemeOffTitle
    override val commandDarkThemeOffDetails: String
        get() = getCurrentResources().commandDarkThemeOffDetails
    override val commandWifiOnTitle: String
        get() = getCurrentResources().commandWifiOnTitle
    override val commandWifiOnDetails: String
        get() = getCurrentResources().commandWifiOnDetails
    override val commandWifiOffTitle: String
        get() = getCurrentResources().commandWifiOffTitle
    override val commandWifiOffDetails: String
        get() = getCurrentResources().commandWifiOffDetails
    override val commandDataOnTitle: String
        get() = getCurrentResources().commandDataOnTitle
    override val commandDataOnDetails: String
        get() = getCurrentResources().commandDataOnDetails
    override val commandDataOffTitle: String
        get() = getCurrentResources().commandDataOffTitle
    override val commandDataOffDetails: String
        get() = getCurrentResources().commandDataOffDetails
    override val commandWifiAndDataOnTitle: String
        get() = getCurrentResources().commandWifiAndDataOnTitle
    override val commandWifiAndDataOnDetails: String
        get() = getCurrentResources().commandWifiAndDataOnDetails
    override val commandWifiAndDataOffTitle: String
        get() = getCurrentResources().commandWifiAndDataOffTitle
    override val commandWifiAndDataOffDetails: String
        get() = getCurrentResources().commandWifiAndDataOffDetails
    override val commandScreenPinningOffTitle: String
        get() = getCurrentResources().commandScreenPinningOffTitle
    override val commandScreenPinningOffDetails: String
        get() = getCurrentResources().commandScreenPinningOffDetails
    override val commandEnableThreeButtonNavigationTitle: String
        get() = getCurrentResources().commandEnableThreeButtonNavigationTitle
    override val commandEnableThreeButtonNavigationDetails: String
        get() = getCurrentResources().commandEnableThreeButtonNavigationDetails
    override val commandEnableTwoButtonNavigationTitle: String
        get() = getCurrentResources().commandEnableTwoButtonNavigationTitle
    override val commandEnableTwoButtonNavigationDetails: String
        get() = getCurrentResources().commandEnableTwoButtonNavigationDetails
    override val commandEnableGestureNavigationTitle: String
        get() = getCurrentResources().commandEnableGestureNavigationTitle
    override val commandEnableGestureNavigationDetails: String
        get() = getCurrentResources().commandEnableGestureNavigationDetails

    override val textCommandStartEventFormat: String
        get() = getCurrentResources().textCommandStartEventFormat
    override val textCommandEndEventFormat: String
        get() = getCurrentResources().textCommandEndEventFormat
    override val textCommandErrorEventFormat: String
        get() = getCurrentResources().textCommandErrorEventFormat

    override val keyCommandStartEventFormat: String
        get() = getCurrentResources().keyCommandStartEventFormat
    override val keyCommandEndEventFormat: String
        get() = getCurrentResources().keyCommandEndEventFormat
    override val keyCommandErrorEventFormat: String
        get() = getCurrentResources().keyCommandErrorEventFormat

    override val screenshotCommandStartEventFormat: String
        get() = getCurrentResources().screenshotCommandStartEventFormat
    override val screenshotCommandEndEventFormat: String
        get() = getCurrentResources().screenshotCommandEndEventFormat
    override val screenshotCommandErrorEventFormat: String
        get() = getCurrentResources().screenshotCommandErrorEventFormat
    override val screenshotCopyToClipbaordEventFormat: String
        get() = getCurrentResources().screenshotCopyToClipbaordEventFormat
    override val cantScreenshotCopyToClipbaordEventFormat: String
        get() = getCurrentResources().cantScreenshotCopyToClipbaordEventFormat
    override val screenshotClearCache: String
        get() = getCurrentResources().screenshotClearCache

    override val menuCommandTitle: String
        get() = getCurrentResources().menuCommandTitle
    override val menuInputTextTitle: String
        get() = getCurrentResources().menuInputTextTitle
    override val menuScreenshot: String
        get() = getCurrentResources().menuScreenshot
    override val settingLanguageHeader: String
        get() = getCurrentResources().settingLanguageHeader
    override val settingLanguageEnglish: String
        get() = getCurrentResources().settingLanguageEnglish
    override val settingLanguageJapanese: String
        get() = getCurrentResources().settingLanguageJapanese
    override val settingLanguageChinese: String
        get() = getCurrentResources().settingLanguageChinese
    override val settingLanguageTurkish: String
        get() = getCurrentResources().settingLanguageTurkish
    override val settingAppearanceHeader: String
        get() = getCurrentResources().settingAppearanceHeader
    override val settingAdbHeader: String
        get() = getCurrentResources().settingAdbHeader
    override val settingAdbDirectoryPathTitle: String
        get() = getCurrentResources().settingAdbDirectoryPathTitle
    override val settingAdbPortNumberTitle: String
        get() = getCurrentResources().settingAdbPortNumberTitle
    override val settingAdbRestartTitle: String
        get() = getCurrentResources().settingAdbRestartTitle
    override val settingAndroidSdkHeader: String
        get() = getCurrentResources().settingAndroidSdkHeader
    override val settingAndroidSdkDirectoryPathTitle: String
        get() = getCurrentResources().settingAndroidSdkDirectoryPathTitle
    override val adbErrorTitle: String
        get() = getCurrentResources().adbErrorTitle
    override val adbErrorMessage: String
        get() = getCurrentResources().adbErrorMessage
    override val adbErrorOpenSetting: String
        get() = getCurrentResources().adbErrorOpenSetting

    override val textCommandOptionNewLine: String
        get() = getCurrentResources().textCommandOptionNewLine
    override val textCommandOptionTab: String
        get() = getCurrentResources().textCommandOptionTab

    override val sortByNameAsc: String
        get() = getCurrentResources().sortByNameAsc

    override val sortByNameDesc: String
        get() = getCurrentResources().sortByNameDesc

    // TopSection tooltips
    override val tooltipRefresh: String
        get() = getCurrentResources().tooltipRefresh
    override val tooltipPower: String
        get() = getCurrentResources().tooltipPower
    override val tooltipVolumeUp: String
        get() = getCurrentResources().tooltipVolumeUp
    override val tooltipVolumeDown: String
        get() = getCurrentResources().tooltipVolumeDown
    override val tooltipVolumeMute: String
        get() = getCurrentResources().tooltipVolumeMute
    override val tooltipBack: String
        get() = getCurrentResources().tooltipBack
    override val tooltipHome: String
        get() = getCurrentResources().tooltipHome
    override val tooltipRecents: String
        get() = getCurrentResources().tooltipRecents

    private var currentType: Type = Type.ENGLISH

    fun switch(type: Type) {
        currentType = type
    }

    private fun getCurrentResources(): StringResources =
        when (currentType) {
            Type.ENGLISH -> EnglishResources
            Type.JAPANESE -> JapaneseResources
            Type.CHINESE -> ChineseResources
            Type.TURKISH -> TurkishResources
        }

    enum class Type {
        ENGLISH,
        JAPANESE,
        CHINESE,
        TURKISH,
    }
}
