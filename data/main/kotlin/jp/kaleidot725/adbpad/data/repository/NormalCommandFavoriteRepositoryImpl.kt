package jp.kaleidot725.adbpad.data.repository

import jp.kaleidot725.adbpad.data.local.NormalCommandFavoriteFileCreator
import jp.kaleidot725.adbpad.domain.model.command.NormalCommand
import jp.kaleidot725.adbpad.domain.repository.NormalCommandFavoriteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NormalCommandFavoriteRepositoryImpl : NormalCommandFavoriteRepository {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val _favorites = MutableStateFlow<List<String>>(emptyList())
    override val favorites: StateFlow<List<String>> = _favorites.asStateFlow()

    init {
        scope.launch {
            _favorites.value = NormalCommandFavoriteFileCreator.load()
        }
    }

    override suspend fun save(command: NormalCommand): Boolean {
        return withContext(Dispatchers.IO) {
            val currentFavorites = _favorites.value.toMutableList()
            val className = command::class.qualifiedName ?: return@withContext false
            if (!currentFavorites.contains(className)) {
                currentFavorites.add(className)
                val saved = NormalCommandFavoriteFileCreator.save(currentFavorites)
                if (saved) {
                    _favorites.value = currentFavorites
                }
                return@withContext saved
            }
            return@withContext false
        }
    }

    override suspend fun delete(command: NormalCommand): Boolean {
        return withContext(Dispatchers.IO) {
            val currentFavorites = _favorites.value.toMutableList()
            val className = command::class.qualifiedName ?: return@withContext false
            if (currentFavorites.contains(className)) {
                currentFavorites.remove(className)
                val saved = NormalCommandFavoriteFileCreator.save(currentFavorites)
                if (saved) {
                    _favorites.value = currentFavorites
                }
                return@withContext saved
            }
            return@withContext false
        }
    }
}
