package jp.kaleidot725.adbpad.model.usecase

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.request.shell.v1.ShellCommandRequest
import jp.kaleidot725.adbpad.model.data.InputText

class ExecuteInputTextUseCase {
    suspend operator fun invoke(serial: String?, inputText: InputText): Boolean {
        val adb = AndroidDebugBridgeClientFactory().build()
        val response = adb.execute(
            request = ShellCommandRequest("input text ${inputText.content}"),
            serial = serial
        )
        return response.exitCode == 0
    }
}
