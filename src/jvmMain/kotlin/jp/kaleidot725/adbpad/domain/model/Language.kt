package jp.kaleidot725.adbpad.domain.model

object Language {
    const val WINDOW_TITLE = "AdbPad(v0.1.0)"
    const val NOT_FOUND_DEVICE = "Not found device"
    const val NOT_FOUND_COMMAND = "Not found command"
    const val NOT_FOUND_INPUT_TEXT = "Not found input text"
    const val NOT_FOUND_SCREEN_SHOT = "Not found screenshot"
    const val EXECUTE = "Run"
    const val SAVE = "Save"
    const val DELETE = "Delete"
    const val SEND = "Send"
    const val TARGET_DEVICE = "Target Device"
    const val TOOL = "Tools"
    const val TAKE_SCREENSHOT = "Shoot"
    const val TAKE_THEME_SCREENSHOT = "Shoot(each theme)"

    const val COMMAND_START_EVENT_FORMAT = "Start sending command 「%s」"
    const val COMMAND_END_EVENT_FORMAT = "End sending command 「%s」"
    const val COMMAND_ERROR_EVENT_FORMAT = "Error sending command 「%s」"

    const val COMMAND_LAYOUT_BORDER_ON_TITLE = "Show layout bounds: ON"
    const val COMMAND_LAYOUT_BORDER_ON_DETAILS = "Enable showing clip bounds, margins, etc."
    const val COMMAND_LAYOUT_BORDER_OFF_TITLE = "Show layout bonds: OFF"
    const val COMMAND_LAYOUT_BORDER_OFF_DETAILS = "Disable showing clip bounds, margins, etc."
    const val COMMAND_TAP_EFFECT_ON_TITLE = "Show taps: ON"
    const val COMMAND_TAP_EFFECT_ON_DETAILS = "Enable showing visual feedback for taps."
    const val COMMAND_TAP_EFFECT_OFF_TITLE = "Show taps: OFF"
    const val COMMAND_TAP_EFFECT_OFF_DETAILS = "Disable showing visual feedback for taps."
    const val COMMAND_SLEEP_MODE_ON_TITLE = "Sleep mode: ON"
    const val COMMAND_SLEEP_MODE_ON_DETAILS = "Enable sleep mode and device go into sleep."
    const val COMMAND_SLEEP_MODE_OFF_TITLE = "Sleep mode: OFF"
    const val COMMAND_SLEEP_MODE_OFF_DETAILS = "Disable sleep mode and device doesn't go into sleep."
    const val COMMAND_DARK_THEME_ON_TITLE = "Dark theme: ON"
    const val COMMAND_DARK_THEME_ON_DETAILS = "Enable dark theme."
    const val COMMAND_DARK_THEME_OFF_TITLE = "Dark theme: OFF"
    const val COMMAND_DARK_THEME_OFF_DETAILS = "Disable dark theme."
    const val COMMAND_WIFI_ON_TITLE = "Wi-Fi: ON"
    const val COMMAND_WIFI_ON_DETAILS = "Enable Wi-Fi communication."
    const val COMMAND_WIFI_OFF_TITLE = "Wi-Fi: OFF"
    const val COMMAND_WIFI_OFF_DETAILS = "Disable Wi-Fi communication."
    const val COMMAND_DATA_ON_TITLE = "Cellular: ON"
    const val COMMAND_DATA_ON_DETAILS = "Enable cellular communication."
    const val COMMAND_DATA_OFF_TITLE = "Cellular: OFF"
    const val COMMAND_DATA_OFF_DETAILS = "Disable cellular communication."
    const val COMMAND_WIFI_AND_DATA_ON_TITLE = "Wi-Fi and cellular: ON"
    const val COMMAND_WIFI_AND_DATA_ON_DETAILS = "Enable Wi-Fi and cellular communication."
    const val COMMAND_WIFI_AND_DATA_OFF_TITLE = "Wi-Fi and cellular: OFF"
    const val COMMAND_WIFI_AND_DATA_OFF_DETAILS = "Disable Wi-Fi and cellular communication."

    const val TEXT_COMMAND_START_EVENT_FORMAT = "Start sending text「%s」"
    const val TEXT_COMMAND_END_EVENT_FORMAT = "End sending text「%s」"
    const val TEXT_COMMAND_ERROR_EVENT_FORMAT = "Error sending text「%s」"

    const val MENU_COMMAND_TITLE = "Command"
    const val MENU_INPUT_TEXT_TITLE = "Send Text"
    const val MENU_SCREENSHOT = "Screenshot"
}
