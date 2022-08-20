package jp.kaleidot725.adbpad.model.data

sealed class Command() {
    object DarkThemeOn : Command()

    object DarkThemeOff : Command()

    object WifiOn : Command()

    object WifiOff : Command()

    object DataOn : Command()

    object DataOff : Command()

    object WifiAndDataOn : Command()

    object WifiAndDataOff : Command()
}
