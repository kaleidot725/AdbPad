package jp.kaleidot725.adbpad.domain.usecase.app

import jp.kaleidot725.adbpad.domain.repository.ScrcpyProcessRepository

class ShutdownAppUseCase(
    private val scrcpyProcessRepository: ScrcpyProcessRepository,
) {
    operator fun invoke() {
        // Terminate all running Scrcpy processes
        scrcpyProcessRepository.terminateAllProcesses()
        
        println("Application shutdown complete.")
    }
}