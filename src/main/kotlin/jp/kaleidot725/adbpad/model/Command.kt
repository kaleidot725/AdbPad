package jp.kaleidot725.adbpad.model

enum class Command {
    DARK_THEME_ON,
    DARK_THEME_OFF,
    C,
    D,
    E,
    F,
    G
}

fun Command.toTitle(): String {
    return when (this) {
        Command.DARK_THEME_ON -> "ダークテーマON"
        Command.DARK_THEME_OFF -> "ダークテーマOFF"
        else -> "不明"
    }
}

fun Command.toDetail(): String {
    return when (this) {
        Command.DARK_THEME_ON -> "端末のダークテーマ設定をONにします"
        Command.DARK_THEME_OFF -> "端末のダークテーマ設定をOFFにします"
        else -> "不明"
    }
}
