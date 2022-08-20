package jp.kaleidot725.adbpad.model.usecase

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.request.shell.v1.ShellCommandRequest
import jp.kaleidot725.adbpad.model.data.Command

class ExecuteCommandUseCase {
    suspend operator fun invoke(serial: String?, command: Command): Boolean {
        val adb = AndroidDebugBridgeClientFactory().build()
        when (command) {
            Command.DarkThemeOn -> {
                adb.execute(
                    request = ShellCommandRequest("cmd uimode night yes"),
                    serial = serial
                )
            }

            Command.DarkThemeOff -> {
                adb.execute(
                    request = ShellCommandRequest("cmd uimode night no"),
                    serial = serial
                )
            }

            Command.WifiOn -> {
                adb.execute(
                    request = ShellCommandRequest("svc wifi enable"),
                    serial = serial
                )
            }

            Command.WifiOff -> {
                adb.execute(
                    request = ShellCommandRequest("svc wifi disable"),
                    serial = serial
                )
            }

            Command.DataOn -> {
                adb.execute(
                    request = ShellCommandRequest("svc data enable"),
                    serial = serial
                )
            }

            Command.DataOff -> {
                adb.execute(
                    request = ShellCommandRequest("svc data disable"),
                    serial = serial
                )
            }

            Command.WifiAndDataOn -> {
                adb.execute(
                    request = ShellCommandRequest("svc wifi enable"),
                    serial = serial
                )
                adb.execute(
                    request = ShellCommandRequest("svc data enable"),
                    serial = serial
                )
            }

            Command.WifiAndDataOff -> {
                adb.execute(
                    request = ShellCommandRequest("svc wifi disable"),
                    serial = serial
                )
                adb.execute(
                    request = ShellCommandRequest("svc data disable"),
                    serial = serial
                )
            }
        }
        return true // FIXME 正しい結果を戻り値にする
    }
}