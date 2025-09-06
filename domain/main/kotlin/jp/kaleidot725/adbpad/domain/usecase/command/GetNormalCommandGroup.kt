package jp.kaleidot725.adbpad.domain.usecase.command

import jp.kaleidot725.adbpad.domain.model.command.NormalCommandCategory
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandGroup
import jp.kaleidot725.adbpad.domain.repository.NormalCommandRepository

class GetNormalCommandGroup(
    private val normalCommandRepository: NormalCommandRepository,
) {
    operator fun invoke(): NormalCommandGroup {
        val all = normalCommandRepository.getCommands()
        return NormalCommandGroup(
            all = all,
            ui = all.filter { it.category == NormalCommandCategory.UI },
            communication = all.filter { it.category == NormalCommandCategory.COM },
        )
    }
}
