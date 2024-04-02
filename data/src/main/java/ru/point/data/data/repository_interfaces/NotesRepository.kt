package ru.point.data.data.repository_interfaces

import ru.point.data.data.entities.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getNoteById(id: Long): Flow<Note>
    fun getNotes(): Flow<List<Note>>
    fun getNotes(folderId: Long): Flow<List<Note>>
    fun createNote(note: Note)
    fun updateNote(note: Note)
    fun deleteNote(id: Long)
    fun getNoteCount(): Flow<Int>
}