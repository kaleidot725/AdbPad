package jp.kaleidot725.adbpad.ui.model

sealed class Dialog {
    object Setting : Dialog()

    object AdbError : Dialog()
}
