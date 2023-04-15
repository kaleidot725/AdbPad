package jp.kaleidot725.adbpad.domain.model.language

import jp.kaleidot725.adbpad.domain.model.language.resources.EnglishResources
import jp.kaleidot725.adbpad.domain.model.language.resources.JapaneseResources
import jp.kaleidot725.adbpad.domain.model.language.resources.StringResources

object Language : StringResources {
    override val VERSION: String
        get() = getCurrentResources().VERSION
    override val WINDOW_TITLE: String
        get() = getCurrentResources().WINDOW_TITLE
    override val NOT_FOUND_DEVICE: String
        get() = getCurrentResources().NOT_FOUND_DEVICE
    override val NOT_FOUND_COMMAND: String
        get() = getCurrentResources().NOT_FOUND_COMMAND
    override val NOT_FOUND_INPUT_TEXT: String
        get() = getCurrentResources().NOT_FOUND_INPUT_TEXT
    override val NOT_FOUND_SCREEN_SHOT: String
        get() = getCurrentResources().NOT_FOUND_SCREEN_SHOT
    override val EXECUTE: String
        get() = getCurrentResources().EXECUTE
    override val SAVE: String
        get() = getCurrentResources().SAVE
    override val DELETE: String
        get() = getCurrentResources().DELETE
    override val SEND: String
        get() = getCurrentResources().SEND
    override val CANCEL: String
        get() = getCurrentResources().CANCEL
    override val TARGET_DEVICE: String
        get() = getCurrentResources().TARGET_DEVICE
    override val TOOL: String
        get() = getCurrentResources().TOOL
    override val SETTING: String
        get() = getCurrentResources().SETTING
    override val DARK: String
        get() = getCurrentResources().DARK
    override val LIGHT: String
        get() = getCurrentResources().LIGHT
    override val SYSTEM: String
        get() = getCurrentResources().SYSTEM
    override val SCREENSHOT_TAKE_BY_CURRENT_THEME: String
        get() = getCurrentResources().SCREENSHOT_TAKE_BY_CURRENT_THEME
    override val SCREENSHOT_TAKE_BY_DARK_THEME: String
        get() = getCurrentResources().SCREENSHOT_TAKE_BY_DARK_THEME
    override val SCREENSHOT_TAKE_BY_LIGHT_THEME: String
        get() = getCurrentResources().SCREENSHOT_TAKE_BY_LIGHT_THEME
    override val SCREENSHOT_TAKE_BY_BOTH_THEME: String
        get() = getCurrentResources().SCREENSHOT_TAKE_BY_BOTH_THEME
    override val COMMAND_START_EVENT_FORMAT: String
        get() = getCurrentResources().COMMAND_START_EVENT_FORMAT
    override val COMMAND_END_EVENT_FORMAT: String
        get() = getCurrentResources().COMMAND_END_EVENT_FORMAT
    override val COMMAND_ERROR_EVENT_FORMAT: String
        get() = getCurrentResources().COMMAND_ERROR_EVENT_FORMAT
    override val COMMAND_LAYOUT_BORDER_ON_TITLE: String
        get() = getCurrentResources().COMMAND_LAYOUT_BORDER_ON_TITLE
    override val COMMAND_LAYOUT_BORDER_ON_DETAILS: String
        get() = getCurrentResources().COMMAND_LAYOUT_BORDER_ON_DETAILS
    override val COMMAND_LAYOUT_BORDER_OFF_TITLE: String
        get() = getCurrentResources().COMMAND_LAYOUT_BORDER_OFF_TITLE
    override val COMMAND_LAYOUT_BORDER_OFF_DETAILS: String
        get() = getCurrentResources().COMMAND_LAYOUT_BORDER_OFF_DETAILS
    override val COMMAND_TAP_EFFECT_ON_TITLE: String
        get() = getCurrentResources().COMMAND_TAP_EFFECT_ON_TITLE
    override val COMMAND_TAP_EFFECT_ON_DETAILS: String
        get() = getCurrentResources().COMMAND_TAP_EFFECT_ON_DETAILS
    override val COMMAND_TAP_EFFECT_OFF_TITLE: String
        get() = getCurrentResources().COMMAND_TAP_EFFECT_OFF_TITLE
    override val COMMAND_TAP_EFFECT_OFF_DETAILS: String
        get() = getCurrentResources().COMMAND_TAP_EFFECT_OFF_DETAILS
    override val COMMAND_SLEEP_MODE_ON_TITLE: String
        get() = getCurrentResources().COMMAND_SLEEP_MODE_ON_TITLE
    override val COMMAND_SLEEP_MODE_ON_DETAILS: String
        get() = getCurrentResources().COMMAND_SLEEP_MODE_ON_DETAILS
    override val COMMAND_SLEEP_MODE_OFF_TITLE: String
        get() = getCurrentResources().COMMAND_SLEEP_MODE_OFF_TITLE
    override val COMMAND_SLEEP_MODE_OFF_DETAILS: String
        get() = getCurrentResources().COMMAND_SLEEP_MODE_OFF_DETAILS
    override val COMMAND_DARK_THEME_ON_TITLE: String
        get() = getCurrentResources().COMMAND_DARK_THEME_ON_TITLE
    override val COMMAND_DARK_THEME_ON_DETAILS: String
        get() = getCurrentResources().COMMAND_DARK_THEME_ON_DETAILS
    override val COMMAND_DARK_THEME_OFF_TITLE: String
        get() = getCurrentResources().COMMAND_DARK_THEME_OFF_TITLE
    override val COMMAND_DARK_THEME_OFF_DETAILS: String
        get() = getCurrentResources().COMMAND_DARK_THEME_OFF_DETAILS
    override val COMMAND_WIFI_ON_TITLE: String
        get() = getCurrentResources().COMMAND_WIFI_ON_TITLE
    override val COMMAND_WIFI_ON_DETAILS: String
        get() = getCurrentResources().COMMAND_WIFI_ON_DETAILS
    override val COMMAND_WIFI_OFF_TITLE: String
        get() = getCurrentResources().COMMAND_WIFI_OFF_TITLE
    override val COMMAND_WIFI_OFF_DETAILS: String
        get() = getCurrentResources().COMMAND_WIFI_OFF_DETAILS
    override val COMMAND_DATA_ON_TITLE: String
        get() = getCurrentResources().COMMAND_DATA_ON_TITLE
    override val COMMAND_DATA_ON_DETAILS: String
        get() = getCurrentResources().COMMAND_DATA_ON_DETAILS
    override val COMMAND_DATA_OFF_TITLE: String
        get() = getCurrentResources().COMMAND_DATA_OFF_TITLE
    override val COMMAND_DATA_OFF_DETAILS: String
        get() = getCurrentResources().COMMAND_DATA_OFF_DETAILS
    override val COMMAND_WIFI_AND_DATA_ON_TITLE: String
        get() = getCurrentResources().COMMAND_WIFI_AND_DATA_ON_TITLE
    override val COMMAND_WIFI_AND_DATA_ON_DETAILS: String
        get() = getCurrentResources().COMMAND_WIFI_AND_DATA_ON_DETAILS
    override val COMMAND_WIFI_AND_DATA_OFF_TITLE: String
        get() = getCurrentResources().COMMAND_WIFI_AND_DATA_OFF_TITLE
    override val COMMAND_WIFI_AND_DATA_OFF_DETAILS: String
        get() = getCurrentResources().COMMAND_WIFI_AND_DATA_OFF_DETAILS
    override val COMMAND_SCREEN_PINNING_OFF_TITLE: String
        get() = getCurrentResources().COMMAND_SCREEN_PINNING_OFF_TITLE
    override val COMMAND_SCREEN_PINNING_OFF_DETAILS: String
        get() = getCurrentResources().COMMAND_SCREEN_PINNING_OFF_DETAILS

    override val TEXT_COMMAND_START_EVENT_FORMAT: String
        get() = getCurrentResources().TEXT_COMMAND_START_EVENT_FORMAT
    override val TEXT_COMMAND_END_EVENT_FORMAT: String
        get() = getCurrentResources().TEXT_COMMAND_END_EVENT_FORMAT
    override val TEXT_COMMAND_ERROR_EVENT_FORMAT: String
        get() = getCurrentResources().TEXT_COMMAND_ERROR_EVENT_FORMAT
    override val SCREENSHOT_COMMAND_START_EVENT_FORMAT: String
        get() = getCurrentResources().SCREENSHOT_COMMAND_START_EVENT_FORMAT
    override val SCREENSHOT_COMMAND_END_EVENT_FORMAT: String
        get() = getCurrentResources().SCREENSHOT_COMMAND_END_EVENT_FORMAT
    override val SCREENSHOT_COMMAND_ERROR_EVENT_FORMAT: String
        get() = getCurrentResources().SCREENSHOT_COMMAND_ERROR_EVENT_FORMAT
    override val SCREENSHOT_COPY_TO_CLIPBAORD_EVENT_FORMAT: String
        get() = getCurrentResources().SCREENSHOT_COPY_TO_CLIPBAORD_EVENT_FORMAT
    override val MENU_COMMAND_TITLE: String
        get() = getCurrentResources().MENU_COMMAND_TITLE
    override val MENU_INPUT_TEXT_TITLE: String
        get() = getCurrentResources().MENU_INPUT_TEXT_TITLE
    override val MENU_SCREENSHOT: String
        get() = getCurrentResources().MENU_SCREENSHOT
    override val SETTING_LANGUAGE_HEADER: String
        get() = getCurrentResources().SETTING_LANGUAGE_HEADER
    override val SETTING_LANGUAGE_ENGLISH: String
        get() = getCurrentResources().SETTING_LANGUAGE_ENGLISH
    override val SETTING_LANGUAGE_JAPANESE: String
        get() = getCurrentResources().SETTING_LANGUAGE_JAPANESE
    override val SETTING_APPEARANCE_HEADER: String
        get() = getCurrentResources().SETTING_APPEARANCE_HEADER
    override val SETTING_ADB_HEADER: String
        get() = getCurrentResources().SETTING_ADB_HEADER
    override val SETTING_ADB_DIRECTORY_PATH_TITLE: String
        get() = getCurrentResources().SETTING_ADB_DIRECTORY_PATH_TITLE
    override val SETTING_ADB_PORT_NUMBER_TITLE: String
        get() = getCurrentResources().SETTING_ADB_PORT_NUMBER_TITLE
    override val SETTING_ANDROID_SDK_HEADER: String
        get() = getCurrentResources().SETTING_ANDROID_SDK_HEADER
    override val SETTING_ANDROID_SDK_DIRECTORY_PATH_TITLE: String
        get() = getCurrentResources().SETTING_ANDROID_SDK_DIRECTORY_PATH_TITLE
    override val ADB_ERROR_TITLE: String
        get() = getCurrentResources().ADB_ERROR_TITLE
    override val ADB_ERROR_MESSAGE: String
        get() = getCurrentResources().ADB_ERROR_MESSAGE
    override val ADB_ERROR_OPEN_SETTING: String
        get() = getCurrentResources().ADB_ERROR_OPEN_SETTING

    private var currentType: Type = Type.ENGLISH
    fun switch(type: Type) {
        currentType = type
    }

    private fun getCurrentResources(): StringResources {
        return when (currentType) {
            Type.ENGLISH -> EnglishResources
            Type.JAPANESE -> JapaneseResources
        }
    }

    enum class Type {
        ENGLISH,
        JAPANESE
    }
}
