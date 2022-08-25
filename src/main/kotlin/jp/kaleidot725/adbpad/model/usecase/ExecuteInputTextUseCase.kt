package jp.kaleidot725.adbpad.model.usecase

import com.malinskiy.adam.AndroidDebugBridgeClientFactory
import com.malinskiy.adam.request.shell.v1.ShellCommandRequest
import jp.kaleidot725.adbpad.model.data.InputText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExecuteInputTextUseCase {
    suspend operator fun invoke(serial: String?, inputText: InputText): Boolean {
        return withContext(Dispatchers.IO) {
            val adb = AndroidDebugBridgeClientFactory().build()
            val response = adb.execute(
                request = ShellCommandRequest("input text ${inputText.content}"),
                serial = serial
            )
            return@withContext response.exitCode == 0
        }
    }
}
