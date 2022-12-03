package jp.kaleidot725.adbpad.domain.model


object Language : StringResources by StringResources.Japanese

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

    val SETTING_ADB_HEADER: String
    val SETTING_ADB_DIRECTORY_PATH_TITLE: String
    val SETTING_ADB_PORT_NUMBER_TITLE: String
    val SETTING_ANDROID_SDK_HEADER: String
    val SETTING_ANDROID_SDK_DIRECTORY_PATH_TITLE: String

    val ADB_ERROR_TITLE: String
    val ADB_ERROR_MESSAGE: String
    val ADB_ERROR_OPEN_SETTING: String

    object English : StringResources {
        override val VERSION = "v1.0.0"
        override val WINDOW_TITLE = "AdbPad($VERSION)"

        override val NOT_FOUND_DEVICE = "Not found device"
        override val NOT_FOUND_COMMAND = "Not found command"
        override val NOT_FOUND_INPUT_TEXT = "Not found input text"
        override val NOT_FOUND_SCREEN_SHOT = "Not found screenshot"

        override val EXECUTE = "Run"
        override val SAVE = "Save"
        override val DELETE = "Delete"
        override val SEND = "Send"
        override val CANCEL = "Cancel"
        override val TARGET_DEVICE = "Devices"
        override val TOOL = "Tools"
        override val SETTING = "Setting"
        override val DARK = "Dark"
        override val LIGHT = "Light"
        override val SYSTEM = "System"

        override val SCREENSHOT_TAKE_BY_CURRENT_THEME = "Take by current theme"
        override val SCREENSHOT_TAKE_BY_DARK_THEME = "Take by dark theme"
        override val SCREENSHOT_TAKE_BY_LIGHT_THEME = "Take by light theme"
        override val SCREENSHOT_TAKE_BY_BOTH_THEME = "Take by both theme"

        override val COMMAND_START_EVENT_FORMAT = "Start sending command 「%s」"
        override val COMMAND_END_EVENT_FORMAT = "End sending command 「%s」"
        override val COMMAND_ERROR_EVENT_FORMAT = "Error sending command 「%s」"

        override val COMMAND_LAYOUT_BORDER_ON_TITLE = "Show layout bounds: ON"
        override val COMMAND_LAYOUT_BORDER_ON_DETAILS = "Enable showing clip bounds, margins, etc."
        override val COMMAND_LAYOUT_BORDER_OFF_TITLE = "Show layout bonds: OFF"
        override val COMMAND_LAYOUT_BORDER_OFF_DETAILS = "Disable showing clip bounds, margins, etc."
        override val COMMAND_TAP_EFFECT_ON_TITLE = "Show taps: ON"
        override val COMMAND_TAP_EFFECT_ON_DETAILS = "Enable showing visual feedback for taps."
        override val COMMAND_TAP_EFFECT_OFF_TITLE = "Show taps: OFF"
        override val COMMAND_TAP_EFFECT_OFF_DETAILS = "Disable showing visual feedback for taps."
        override val COMMAND_SLEEP_MODE_ON_TITLE = "Sleep mode: ON"
        override val COMMAND_SLEEP_MODE_ON_DETAILS = "Enable sleep mode and device go into sleep."
        override val COMMAND_SLEEP_MODE_OFF_TITLE = "Sleep mode: OFF"
        override val COMMAND_SLEEP_MODE_OFF_DETAILS = "Disable sleep mode and device doesn't go into sleep."
        override val COMMAND_DARK_THEME_ON_TITLE = "Dark theme: ON"
        override val COMMAND_DARK_THEME_ON_DETAILS = "Enable dark theme."
        override val COMMAND_DARK_THEME_OFF_TITLE = "Dark theme: OFF"
        override val COMMAND_DARK_THEME_OFF_DETAILS = "Disable dark theme."
        override val COMMAND_WIFI_ON_TITLE = "Wi-Fi: ON"
        override val COMMAND_WIFI_ON_DETAILS = "Enable Wi-Fi communication."
        override val COMMAND_WIFI_OFF_TITLE = "Wi-Fi: OFF"
        override val COMMAND_WIFI_OFF_DETAILS = "Disable Wi-Fi communication."
        override val COMMAND_DATA_ON_TITLE = "Cellular: ON"
        override val COMMAND_DATA_ON_DETAILS = "Enable cellular communication."
        override val COMMAND_DATA_OFF_TITLE = "Cellular: OFF"
        override val COMMAND_DATA_OFF_DETAILS = "Disable cellular communication."
        override val COMMAND_WIFI_AND_DATA_ON_TITLE = "Wi-Fi and cellular: ON"
        override val COMMAND_WIFI_AND_DATA_ON_DETAILS = "Enable Wi-Fi and cellular communication."
        override val COMMAND_WIFI_AND_DATA_OFF_TITLE = "Wi-Fi and cellular: OFF"
        override val COMMAND_WIFI_AND_DATA_OFF_DETAILS = "Disable Wi-Fi and cellular communication."

        override val TEXT_COMMAND_START_EVENT_FORMAT = "Start sending text「%s」"
        override val TEXT_COMMAND_END_EVENT_FORMAT = "End sending text「%s」"
        override val TEXT_COMMAND_ERROR_EVENT_FORMAT = "Error sending text「%s」"

        override val SCREENSHOT_COMMAND_START_EVENT_FORMAT = "Start taking screenshot"
        override val SCREENSHOT_COMMAND_END_EVENT_FORMAT = "End taking screenshot"
        override val SCREENSHOT_COMMAND_ERROR_EVENT_FORMAT = "Error taking screenshot"

        override val MENU_COMMAND_TITLE = "Command"
        override val MENU_INPUT_TEXT_TITLE = "Send Text"
        override val MENU_SCREENSHOT = "Screenshot"

        override val SETTING_APPEARANCE_HEADER = "Appearance"

        override val SETTING_ADB_HEADER = "ADB"
        override val SETTING_ADB_DIRECTORY_PATH_TITLE = "Binary Path"
        override val SETTING_ADB_PORT_NUMBER_TITLE = "Server Port"
        override val SETTING_ANDROID_SDK_HEADER = "Android SDK"
        override val SETTING_ANDROID_SDK_DIRECTORY_PATH_TITLE = "Directory Path"

        override val ADB_ERROR_TITLE = "Adb Error"
        override val ADB_ERROR_MESSAGE = "Can't start adb server, Please change adb setting."
        override val ADB_ERROR_OPEN_SETTING = "Open Setting"
    }

    object Japanese : StringResources {
        override val VERSION = "v1.0.0"
        override val WINDOW_TITLE = "AdbPad($VERSION)"

        override val NOT_FOUND_DEVICE = "デバイスがありません"
        override val NOT_FOUND_COMMAND = "コマンドがありません"
        override val NOT_FOUND_INPUT_TEXT = "テキストがありません"
        override val NOT_FOUND_SCREEN_SHOT = "スクリーンショットがありません"

        override val EXECUTE = "実行"
        override val SAVE = "保存"
        override val DELETE = "削除"
        override val SEND = "送信"
        override val CANCEL = "キャンセル"
        override val TARGET_DEVICE = "端末"
        override val TOOL = "ツール"
        override val SETTING = "設定　"
        override val DARK = "Dark"
        override val LIGHT = "Light"
        override val SYSTEM = "System"

        override val SCREENSHOT_TAKE_BY_CURRENT_THEME = "現在のテーマで撮影する"
        override val SCREENSHOT_TAKE_BY_DARK_THEME = "ダークテーマで撮影する"
        override val SCREENSHOT_TAKE_BY_LIGHT_THEME = "ライトテーマで撮影する"
        override val SCREENSHOT_TAKE_BY_BOTH_THEME = "両方のテーマで撮影する"

        override val COMMAND_START_EVENT_FORMAT = "「%s」のコマンド送信を開始しました"
        override val COMMAND_END_EVENT_FORMAT = "「%s」のコマンド送信が完了しました"
        override val COMMAND_ERROR_EVENT_FORMAT = "「%s」のコマンド送信に失敗しました"

        override val COMMAND_LAYOUT_BORDER_ON_TITLE = "レイアウト境界表示: ON"
        override val COMMAND_LAYOUT_BORDER_ON_DETAILS = "レイアウトの境界やマージンなどの表示を有効化します"
        override val COMMAND_LAYOUT_BORDER_OFF_TITLE = "レイアウト境界表示: OFF"
        override val COMMAND_LAYOUT_BORDER_OFF_DETAILS = "レイアウトの境界やマージンなどの表示を無効化します"
        override val COMMAND_TAP_EFFECT_ON_TITLE = "タップ表示: ON"
        override val COMMAND_TAP_EFFECT_ON_DETAILS = "タップ表示を有効化します"
        override val COMMAND_TAP_EFFECT_OFF_TITLE = "タップ表示: OFF"
        override val COMMAND_TAP_EFFECT_OFF_DETAILS = "タップ表示を無効化します"
        override val COMMAND_SLEEP_MODE_ON_TITLE = "スリープモード: ON"
        override val COMMAND_SLEEP_MODE_ON_DETAILS = "端末がスリープモードに移行可能にする"
        override val COMMAND_SLEEP_MODE_OFF_TITLE = "スリープモード: OFF"
        override val COMMAND_SLEEP_MODE_OFF_DETAILS = "端末がスリープモードに移行不可能にする"
        override val COMMAND_DARK_THEME_ON_TITLE = "ダークテーマ: ON"
        override val COMMAND_DARK_THEME_ON_DETAILS = "端末のテーマをダークテーマにする"
        override val COMMAND_DARK_THEME_OFF_TITLE = "ダークテーマ: OFF"
        override val COMMAND_DARK_THEME_OFF_DETAILS = "端末のテーマをライトテーマにする"
        override val COMMAND_WIFI_ON_TITLE = "Wi-Fi: ON"
        override val COMMAND_WIFI_ON_DETAILS = "Wi-Fi通信を有効化する"
        override val COMMAND_WIFI_OFF_TITLE = "Wi-Fi: OFF"
        override val COMMAND_WIFI_OFF_DETAILS = "Wi-Fi通信を無効化する"
        override val COMMAND_DATA_ON_TITLE = "モバイル通信: ON"
        override val COMMAND_DATA_ON_DETAILS = "モバイル通信を有効化する"
        override val COMMAND_DATA_OFF_TITLE = "モバイル通信: OFF"
        override val COMMAND_DATA_OFF_DETAILS = "モバイル通信を無効化する"
        override val COMMAND_WIFI_AND_DATA_ON_TITLE = "Wi-Fi&モバイル通信: ON"
        override val COMMAND_WIFI_AND_DATA_ON_DETAILS = "Wi-Fi通信とモバイル通信の両方を有効化する"
        override val COMMAND_WIFI_AND_DATA_OFF_TITLE = "Wi-Fi&モバイル通信: OFF"
        override val COMMAND_WIFI_AND_DATA_OFF_DETAILS = "Wi-Fi通信とモバイル通信の両方を無効化する"

        override val TEXT_COMMAND_START_EVENT_FORMAT = "「%s」のテキスト送信を開始しました"
        override val TEXT_COMMAND_END_EVENT_FORMAT = "「%s」のテキスト送信が完了しました"
        override val TEXT_COMMAND_ERROR_EVENT_FORMAT = "「%s」のテキスト送信に失敗しました"

        override val SCREENSHOT_COMMAND_START_EVENT_FORMAT = "スクリーンショットの撮影を開始しました"
        override val SCREENSHOT_COMMAND_END_EVENT_FORMAT = "スクリーンショットの撮影が完了しました"
        override val SCREENSHOT_COMMAND_ERROR_EVENT_FORMAT = "スクリーンショットの撮影に失敗しました"

        override val MENU_COMMAND_TITLE = "コマンド"
        override val MENU_INPUT_TEXT_TITLE = "テキスト送信"
        override val MENU_SCREENSHOT = "スクリーンショット"

        override val SETTING_APPEARANCE_HEADER = "テーマ"

        override val SETTING_ADB_HEADER = "ADB"
        override val SETTING_ADB_DIRECTORY_PATH_TITLE = "Binary Path"
        override val SETTING_ADB_PORT_NUMBER_TITLE = "Server Port"
        override val SETTING_ANDROID_SDK_HEADER = "Android SDK"
        override val SETTING_ANDROID_SDK_DIRECTORY_PATH_TITLE = "Directory Path"

        override val ADB_ERROR_TITLE = "ADBエラー"
        override val ADB_ERROR_MESSAGE = "ADBサーバーを開始できませんでした、ADBの設定を変更してください"
        override val ADB_ERROR_OPEN_SETTING = "設定を開く"
    }
}

