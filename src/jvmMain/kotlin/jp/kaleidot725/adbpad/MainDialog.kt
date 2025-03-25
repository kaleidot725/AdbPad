package jp.kaleidot725.adbpad

sealed class MainDialog {
    data object Device : MainDialog()

    data object Setting : MainDialog()

    data object AdbError : MainDialog()
}
