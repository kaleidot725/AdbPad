package jp.kaleidot725.adbpad.domain.usecase.command

import jp.kaleidot725.adbpad.domain.repository.CommandRepository

class GetCommandList(
    private val commandRepository: CommandRepository
) {
    operator fun invoke() = commandRepository.getCommands()
}
