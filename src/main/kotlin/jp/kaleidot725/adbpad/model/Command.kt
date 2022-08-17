package jp.kaleidot725.adbpad.model

enum class Command {
    A,
    B,
    C,
    D,
    E,
    F,
    G
}

fun Command.toTitle(): String {
    return when (this) {
        else -> "ダークテーマON"
    }
}

fun Command.toDetail(): String {
    return when (this) {
        else -> "端末のダークテーマ設定をONにします"
    }
}