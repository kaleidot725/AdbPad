package jp.kaleidot725.adbpad.model.data

import com.malinskiy.adam.request.shell.v1.ShellCommandRequest

interface Command {
    val title: String
    val details: String
    val isRunning: Boolean
    val requests: List<ShellCommandRequest>

    data class LayoutBorderOn(override val isRunning: Boolean = false) : Command {
        override val title: String = "レイアウト境界表示 ON"
        override val details: String = "端末のUI要素のレイアウト境界表示をONにする"
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("setprop debug.layout true"),
            ShellCommandRequest("service call activity 1599295570")
        )
    }

    data class LayoutBorderOff(override val isRunning: Boolean = false) : Command {
        override val title: String = "レイアウト境界表示 OFF"
        override val details: String = "端末のUI要素のレイアウト境界表示をOFFにする"
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("setprop debug.layout false"),
            ShellCommandRequest("service call activity 1599295570")
        )
    }

    data class TapEffectOn(override val isRunning: Boolean = false) : Command {
        override val title: String = "タップ表示 ON"
        override val details: String = "端末の画面をタップした位置の表示をONにする"
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("settings put system show_touches 1"),
        )
    }

    data class TapEffectOff(override val isRunning: Boolean = false) : Command {
        override val title: String = "タップ表示 OFF"
        override val details: String = "端末の画面をタップした位置の表示をOFFにする"
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("settings put system show_touches 0"),
        )
    }

    data class SleepModeOff(override val isRunning: Boolean = false) : Command {
        override val title: String = "スリープモードにしない ON"
        override val details: String = "端末をスリープモードにしないようにする"
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("setting put global stay_on_while_plugged_in 7")
        )
    }

    data class SleepModeOn(override val isRunning: Boolean = false) : Command {
        override val title: String = "スリープモードにしない OFF"
        override val details: String = "端末をスリープモードにするようにする"
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("setting put global stay_on_while_plugged_in ０")
        )
    }

    data class DarkThemeOn(override val isRunning: Boolean = false) : Command {
        override val title: String = "ダークテーマON"
        override val details: String = "端末のダークテーマ設定をONにします"
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("cmd uimode night yes"))
    }

    data class DarkThemeOff(override val isRunning: Boolean = false) : Command {
        override val title: String = "ダークテーマOFF"
        override val details: String = "端末のダークテーマ設定をOFFにします"
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("cmd uimode night no"))
    }

    data class WifiOn(override val isRunning: Boolean = false) : Command {
        override val title: String = "Wi-Fi ON"
        override val details: String = "端末のWi-Fi設定をONにします"
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc wifi enable"))
    }

    data class WifiOff(override val isRunning: Boolean = false) : Command {
        override val title: String = "Wi-Fi OFF"
        override val details: String = "端末のWi-Fi設定をOFFにします"
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc wifi disable"))
    }

    data class DataOn(override val isRunning: Boolean = false) : Command {
        override val title: String = "データ通信 ON"
        override val details: String = "端末のデータ通信設定をONにします"
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc data enable"))
    }

    data class DataOff(override val isRunning: Boolean = false) : Command {
        override val title: String = "データ通信 OFF"
        override val details: String = "端末のデータ通信設定をOFFにします"
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("svc data disable"))
    }

    data class InputText(
        private val text: String,
        override val isRunning: Boolean = false,
    ) : Command {
        override val title: String = "テキスト入力"
        override val details: String = "入力したテキストを端末に送信します"
        override val requests: List<ShellCommandRequest> = listOf(ShellCommandRequest("input text $text"))
    }

    data class WifiAndDataOn(override val isRunning: Boolean = false) : Command {
        override val title: String = "Wi-Fi＆データ通信 ON"
        override val details: String = "端末のWi-Fi設定とデータ通信設定の両方をONにします"
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("svc wifi enable"),
            ShellCommandRequest("svc data enable")
        )
    }

    data class WifiAndDataOff(override val isRunning: Boolean = false) : Command {
        override val title: String = "Wi-Fi&データ通信 OFF"
        override val details: String = "端末のWi-Fi設定とデータ通信設定の両方をOFFにします"
        override val requests: List<ShellCommandRequest> = listOf(
            ShellCommandRequest("svc wifi disable"),
            ShellCommandRequest("svc data disable")
        )
    }
}
