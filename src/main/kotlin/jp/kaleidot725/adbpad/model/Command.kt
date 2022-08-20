package jp.kaleidot725.adbpad.model

enum class Command {
    DARK_THEME_ON,
    DARK_THEME_OFF,
    WIFI_ON,
    WIFI_OFF,
    DATA_ON,
    DATA_OFF,
    WIFI_AND_DATA_ON,
    WIFI_AND_DATA_OFF
}

fun Command.toTitle(): String {
    return when (this) {
        Command.DARK_THEME_ON -> "ダークテーマON"
        Command.DARK_THEME_OFF -> "ダークテーマOFF"
        Command.WIFI_ON -> "Wi-Fi ON"
        Command.WIFI_OFF -> "Wi-Fi OFF"
        Command.DATA_ON -> "データ通信 ON"
        Command.DATA_OFF -> "データ通信 OFF"
        Command.WIFI_AND_DATA_ON -> "Wi-Fi＆データ通信 ON"
        Command.WIFI_AND_DATA_OFF -> "Wi-Fi&データ通信 OFF"
    }
}

fun Command.toDetail(): String {
    return when (this) {
        Command.DARK_THEME_ON -> "端末のダークテーマ設定をONにします"
        Command.DARK_THEME_OFF -> "端末のダークテーマ設定をOFFにします"
        Command.WIFI_ON -> "端末のWi-Fi設定をONにします"
        Command.WIFI_OFF -> "端末のWi-Fi設定をOFFにします"
        Command.DATA_ON -> "端末のデータ通信設定をONにします"
        Command.DATA_OFF -> "端末のデータ通信設定をOFFにします"
        Command.WIFI_AND_DATA_ON -> "端末のWi-Fi設定とデータ通信設定の両方をONにします"
        Command.WIFI_AND_DATA_OFF -> "端末のWi-Fi設定とデータ通信設定の両方をOFFにします"
    }
}
