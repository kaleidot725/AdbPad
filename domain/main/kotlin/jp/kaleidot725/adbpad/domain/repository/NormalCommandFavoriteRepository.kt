package jp.kaleidot725.adbpad.domain.repository

import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import kotlinx.coroutines.flow.StateFlow

interface NormalCommandFavoriteRepository {
    val favorites: StateFlow<List<String>>

    suspend fun save(command: NormalCommand): Boolean

    suspend fun delete(command: NormalCommand): Boolean
}
