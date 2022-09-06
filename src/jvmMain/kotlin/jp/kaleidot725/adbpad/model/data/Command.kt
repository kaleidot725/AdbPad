package jp.kaleidot725.adbpad.model.data

import com.malinskiy.adam.request.shell.v1.ShellCommandRequest

sealed class Command(
    val title: String,
    val details: String,
    val requests: List<ShellCommandRequest>
) {
    object DarkThemeOn : Command(
        "ダークテーマON",
        "端末のダークテーマ設定をONにします",
        listOf(ShellCommandRequest("cmd uimode night yes"))
    )

    object DarkThemeOff : Command(
        "ダークテーマOFF",
        "端末のダークテーマ設定をOFFにします",
        listOf(ShellCommandRequest("cmd uimode night no"))
    )

    object WifiAndDataOn : Command(
        "Wi-Fi＆データ通信 ON",
        "端末のWi-Fi設定とデータ通信設定の両方をONにします",
        listOf(
            ShellCommandRequest("svc wifi enable"),
            ShellCommandRequest("svc data enable")
        )
    )

    object WifiAndDataOff : Command(
        "Wi-Fi&データ通信 OFF",
        "端末のWi-Fi設定とデータ通信設定の両方をOFFにします",
        listOf(
            ShellCommandRequest("svc wifi disable"),
            ShellCommandRequest("svc data disable")
        )
    )
}
