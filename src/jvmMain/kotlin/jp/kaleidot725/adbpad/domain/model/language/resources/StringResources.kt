package jp.kaleidot725.adbpad.domain.model.language.resources

interface StringResources {
    val VERSION: String
    val WINDOW_TITLE: String

    val NOT_FOUND_DEVICE: String
    val NOT_FOUND_COMMAND: String
    val NOT_FOUND_INPUT_TEXT: String
    val NOT_FOUND_SCREEN_SHOT: String
    val EXECUTE: String
    val SAVE: String
    val DELETE: String
    val SEND: String
    val CANCEL: String
    val TARGET_DEVICE: String
    val TOOL: String
    val SETTING: String
    val DARK: String
    val LIGHT: String
    val SYSTEM: String

    val SCREENSHOT_TAKE_BY_CURRENT_THEME: String
    val SCREENSHOT_TAKE_BY_DARK_THEME: String
    val SCREENSHOT_TAKE_BY_LIGHT_THEME: String
    val SCREENSHOT_TAKE_BY_BOTH_THEME: String

    val COMMAND_START_EVENT_FORMAT: String
    val COMMAND_END_EVENT_FORMAT: String
    val COMMAND_ERROR_EVENT_FORMAT: String

    val COMMAND_LAYOUT_BORDER_ON_TITLE: String
    val COMMAND_LAYOUT_BORDER_ON_DETAILS: String
    val COMMAND_LAYOUT_BORDER_OFF_TITLE: String
    val COMMAND_LAYOUT_BORDER_OFF_DETAILS: String
    val COMMAND_TAP_EFFECT_ON_TITLE: String
    val COMMAND_TAP_EFFECT_ON_DETAILS: String
    val COMMAND_TAP_EFFECT_OFF_TITLE: String
    val COMMAND_TAP_EFFECT_OFF_DETAILS: String
    val COMMAND_SLEEP_MODE_ON_TITLE: String
    val COMMAND_SLEEP_MODE_ON_DETAILS: String
    val COMMAND_SLEEP_MODE_OFF_TITLE: String
    val COMMAND_SLEEP_MODE_OFF_DETAILS: String
    val COMMAND_DARK_THEME_ON_TITLE: String
    val COMMAND_DARK_THEME_ON_DETAILS: String
    val COMMAND_DARK_THEME_OFF_TITLE: String
    val COMMAND_DARK_THEME_OFF_DETAILS: String
    val COMMAND_WIFI_ON_TITLE: String
    val COMMAND_WIFI_ON_DETAILS: String
    val COMMAND_WIFI_OFF_TITLE: String
    val COMMAND_WIFI_OFF_DETAILS: String
    val COMMAND_DATA_ON_TITLE: String
    val COMMAND_DATA_ON_DETAILS: String
    val COMMAND_DATA_OFF_TITLE: String
    val COMMAND_DATA_OFF_DETAILS: String
    val COMMAND_WIFI_AND_DATA_ON_TITLE: String
    val COMMAND_WIFI_AND_DATA_ON_DETAILS: String
    val COMMAND_WIFI_AND_DATA_OFF_TITLE: String
    val COMMAND_WIFI_AND_DATA_OFF_DETAILS: String
    val COMMAND_SCREEN_PINNING_OFF_TITLE: String
    val COMMAND_SCREEN_PINNING_OFF_DETAILS: String

    val TEXT_COMMAND_START_EVENT_FORMAT: String
    val TEXT_COMMAND_END_EVENT_FORMAT: String
    val TEXT_COMMAND_ERROR_EVENT_FORMAT: String

    val SCREENSHOT_COMMAND_START_EVENT_FORMAT: String
    val SCREENSHOT_COMMAND_END_EVENT_FORMAT: String
    val SCREENSHOT_COMMAND_ERROR_EVENT_FORMAT: String

    val MENU_COMMAND_TITLE: String
    val MENU_INPUT_TEXT_TITLE: String
    val MENU_SCREENSHOT: String

    val SETTING_APPEARANCE_HEADER: String

    val SETTING_LANGUAGE_HEADER: String
    val SETTING_LANGUAGE_ENGLISH: String
    val SETTING_LANGUAGE_JAPANESE: String

    val SETTING_ADB_HEADER: String
    val SETTING_ADB_DIRECTORY_PATH_TITLE: String
    val SETTING_ADB_PORT_NUMBER_TITLE: String
    val SETTING_ANDROID_SDK_HEADER: String
    val SETTING_ANDROID_SDK_DIRECTORY_PATH_TITLE: String

    val ADB_ERROR_TITLE: String
    val ADB_ERROR_MESSAGE: String
    val ADB_ERROR_OPEN_SETTING: String
}