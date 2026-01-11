package jp.kaleidot725.adbpad.domain.usecase.command

import jp.kaleidot725.adbpad.domain.model.command.NormalCommandCategory
import jp.kaleidot725.adbpad.domain.model.command.NormalCommandGroup
import jp.kaleidot725.adbpad.domain.repository.NormalCommandFavoriteRepository
import jp.kaleidot725.adbpad.domain.repository.NormalCommandRepository

class GetNormalCommandGroup(
    private val normalCommandRepository: NormalCommandRepository,
    private val normalCommandFavoriteRepository: NormalCommandFavoriteRepository,
) {
    suspend operator fun invoke(): NormalCommandGroup {
        val favorites = normalCommandFavoriteRepository.favorites.value
        val all =
            normalCommandRepository.getCommands().map {
                it.updateFavorite(favorites.contains(it::class.qualifiedName))
            }
        return NormalCommandGroup(
            all = all,
            ui = all.filter { it.category == NormalCommandCategory.UI },
            communication = all.filter { it.category == NormalCommandCategory.COM },
            favorite = all.filter { it.isFavorite },
        )
    }
}
