package ru.point.data.data.datasource.repository.interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entity.entities.Note

interface NotesRepository {
    suspend fun getNoteById(id: Long): Flow<Note>
    suspend fun getAllNotes(): Flow<List<Note>>
    suspend fun getNotesByFolderId(folderId: Long): Flow<List<Note>>
    suspend fun getNoteCount(): Flow<Int>
    suspend fun upsert(note: Note)
    suspend fun delete(id: Long)
}