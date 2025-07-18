package jp.kaleidot725.adbpad.domain.model.language.resources

const val APP_VERSION = "v2.6.0"

interface StringResources {
    val windowTitle: String

    val notFoundDevice: String
    val notFoundCommand: String
    val notFoundInputText: String
    val notFoundScreenshot: String
    val execute: String
    val save: String
    val delete: String
    val tab: String
    val send: String
    val cancel: String
    val targetDevice: String
    val tool: String
    val setting: String
    val dark: String
    val light: String
    val system: String
    val search: String

    val textCommandUnTitle: String
    val screenshotTakeByCurrentTheme: String
    val screenshotTakeByDarkTheme: String
    val screenshotTakeByLightTheme: String
    val screenshotTakeByBothTheme: String

    val commandStartEventFormat: String
    val commandEndEventFormat: String
    val commandErrorEventFormat: String
    val commandPointerLocationOnTitle: String
    val commandPointerLocationOnDetails: String
    val commandPointerLocationOffTitle: String
    val commandPointerLocationOffDetails: String
    val commandLayoutBorderOnTitle: String
    val commandLayoutBorderOnDetails: String
    val commandLayoutBorderOffTitle: String
    val commandLayoutBorderOffDetails: String
    val commandTapEffectOnTitle: String
    val commandTapEffectOnDetails: String
    val commandTapEffectOffTitle: String
    val commandTapEffectOffDetails: String
    val commandSleepModeOnTitle: String
    val commandSleepModeOnDetails: String
    val commandSleepModeOffTitle: String
    val commandSleepModeOffDetails: String
    val commandDarkThemeOnTitle: String
    val commandDarkThemeOnDetails: String
    val commandDarkThemeOffTitle: String
    val commandDarkThemeOffDetails: String
    val commandWifiOnTitle: String
    val commandWifiOnDetails: String
    val commandWifiOffTitle: String
    val commandWifiOffDetails: String
    val commandDataOnTitle: String
    val commandDataOnDetails: String
    val commandDataOffTitle: String
    val commandDataOffDetails: String
    val commandWifiAndDataOnTitle: String
    val commandWifiAndDataOnDetails: String
    val commandWifiAndDataOffTitle: String
    val commandWifiAndDataOffDetails: String
    val commandScreenPinningOffTitle: String
    val commandScreenPinningOffDetails: String
    val commandEnableThreeButtonNavigationTitle: String
    val commandEnableThreeButtonNavigationDetails: String
    val commandEnableTwoButtonNavigationTitle: String
    val commandEnableTwoButtonNavigationDetails: String
    val commandEnableGestureNavigationTitle: String
    val commandEnableGestureNavigationDetails: String

    val textCommandStartEventFormat: String
    val textCommandEndEventFormat: String
    val textCommandErrorEventFormat: String

    val keyCommandStartEventFormat: String
    val keyCommandEndEventFormat: String
    val keyCommandErrorEventFormat: String

    val screenshotCommandStartEventFormat: String
    val screenshotCommandEndEventFormat: String
    val screenshotCommandErrorEventFormat: String
    val screenshotCopyToClipbaordEventFormat: String
    val cantScreenshotCopyToClipbaordEventFormat: String
    val screenshotClearCache: String

    val menuCommandTitle: String
    val menuInputTextTitle: String
    val menuScreenshot: String

    val settingAppearanceHeader: String
    val settingLanguageHeader: String
    val settingLanguageEnglish: String
    val settingLanguageJapanese: String
    val settingLanguageChinese: String
    val settingLanguageTurkish: String
    val settingAdbHeader: String
    val settingAdbDirectoryPathTitle: String
    val settingAdbPortNumberTitle: String
    val settingAndroidSdkHeader: String
    val settingAndroidSdkDirectoryPathTitle: String
    val settingAdbRestartTitle: String

    val adbErrorTitle: String
    val adbErrorMessage: String
    val adbErrorOpenSetting: String

    val textCommandOptionNewLine: String
    val textCommandOptionTab: String

    val sortByNameAsc: String
    val sortByNameDesc: String

    // TopSection tooltips
    val tooltipRefresh: String
    val tooltipPower: String
    val tooltipVolumeUp: String
    val tooltipVolumeDown: String
    val tooltipVolumeMute: String
    val tooltipBack: String
    val tooltipHome: String
    val tooltipRecents: String

    // NavigationRail tooltips
    val tooltipCommand: String
    val tooltipText: String
    val tooltipScreenshot: String
    val tooltipFile: String
    val tooltipSetting: String

    // MenuBar Window menu
    val menuWindow: String
    val menuWindowMaximize: String
    val menuWindowMinimize: String
    val menuWindowFullscreen: String
    val menuWindowAlwaysOnTop: String
}
