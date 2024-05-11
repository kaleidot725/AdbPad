package jp.kaleidot725.adbpad.domain.usecase.command

import jp.kaleidot725.adbpad.domain.repository.NormalCommandRepository

class GetCommandList(
    private val normalCommandRepository: NormalCommandRepository,
) {
    operator fun invoke() = normalCommandRepository.getCommands()
}
