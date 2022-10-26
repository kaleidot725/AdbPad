package jp.kaleidot725.adbpad.model.data

import com.malinskiy.adam.request.shell.v1.ShellCommandRequest

sealed class Command(
    val title: String,
    val details: String,
    val requests: List<ShellCommandRequest>,
    val isRunning: Boolean
) {
    class LayoutBorderOn(isRunning: Boolean = false) : Command(
        "レイアウト境界表示 ON",
        "端末のUI要素のレイアウト境界表示をONにする",
        listOf(
            ShellCommandRequest("setprop debug.layout true"),
            ShellCommandRequest("service call activity 1599295570")
        ),
        isRunning
    )

    class LayoutBorderOff(isRunning: Boolean = false) : Command(
        "レイアウト境界表示 OFF",
        "端末のUI要素のレイアウト境界表示をOFFにする",
        listOf(
            ShellCommandRequest("setprop debug.layout false"),
            ShellCommandRequest("service call activity 1599295570")
        ),
        isRunning
    )

    class TapEffectOn(isRunning: Boolean = false) : Command(
        "タップ表示 ON",
        "端末の画面をタップした位置の表示をONにする",
        listOf(
            ShellCommandRequest("settings put system show_touches 1"),
        ),
        isRunning
    )

    class TapEffectOff(isRunning: Boolean = false) : Command(
        "タップ表示 OFF",
        "端末の画面をタップした位置の表示をOFFにする",
        listOf(
            ShellCommandRequest("settings put system show_touches 0"),
        ),
        isRunning
    )

    class SleepModeOff(isRunning: Boolean = false) : Command(
        "スリープモードにしない ON",
        "端末をスリープモードにしないようにする",
        listOf(
            ShellCommandRequest("setting put global stay_on_while_plugged_in 7")
        ),
        isRunning
    )

    class SleepModeOn(isRunning: Boolean = false) : Command(
        "スリープモードにしない OFF",
        "端末をスリープモードにするようにする",
        listOf(
            ShellCommandRequest("setting put global stay_on_while_plugged_in ０")
        ),
        isRunning
    )

    class DarkThemeOn(isRunning: Boolean = false) : Command(
        "ダークテーマON",
        "端末のダークテーマ設定をONにします",
        listOf(ShellCommandRequest("cmd uimode night yes")),
        isRunning
    )

    class DarkThemeOff(isRunning: Boolean = false) : Command(
        "ダークテーマOFF",
        "端末のダークテーマ設定をOFFにします",
        listOf(ShellCommandRequest("cmd uimode night no")),
        isRunning
    )

    class WifiOn(isRunning: Boolean = false) : Command(
        "Wi-Fi ON",
        "端末のWi-Fi設定をONにします",
        listOf(ShellCommandRequest("svc wifi enable")),
        isRunning
    )

    class WifiOff(isRunning: Boolean = false) : Command(
        "Wi-Fi OFF",
        "端末のWi-Fi設定をOFFにします",
        listOf(ShellCommandRequest("svc wifi disable")),
        isRunning
    )

    class DataOn(isRunning: Boolean = false) : Command(
        "データ通信 ON",
        "端末のデータ通信設定をONにします",
        listOf(ShellCommandRequest("svc data enable")),
        isRunning
    )

    class DataOff(isRunning: Boolean = false) : Command(
        "データ通信 OFF",
        "端末のデータ通信設定をOFFにします",
        listOf(ShellCommandRequest("svc data disable")),
        isRunning
    )

    data class InputText(private val text: String) : Command(
        "テキスト入力",
        "入力したテキストを端末に送信します",
        listOf(ShellCommandRequest("input text $text")),
        false
    )

    class WifiAndDataOn(isRunning: Boolean = false) : Command(
        "Wi-Fi＆データ通信 ON",
        "端末のWi-Fi設定とデータ通信設定の両方をONにします",
        listOf(
            ShellCommandRequest("svc wifi enable"),
            ShellCommandRequest("svc data enable")
        ),
        isRunning
    )

    class WifiAndDataOff(isRunning: Boolean = false) : Command(
        "Wi-Fi&データ通信 OFF",
        "端末のWi-Fi設定とデータ通信設定の両方をOFFにします",
        listOf(
            ShellCommandRequest("svc wifi disable"),
            ShellCommandRequest("svc data disable")
        ),
        isRunning
    )
}
