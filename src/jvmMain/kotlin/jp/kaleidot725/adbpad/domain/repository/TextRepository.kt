package jp.kaleidot725.adbpad.domain.repository

interface TextRepository {
    suspend fun getAllText(): List<String>

    suspend fun addText(text: String): Boolean

    suspend fun removeText(text: String): Boolean
}