package com.example.appcash.data.repository_interfaces

import com.example.appcash.data.entities.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNoteById(id: Long): Flow<Note>

    fun getNotes(): Flow<List<Note>>

    fun insertLinkedNote(note: Note, folderId: Long)

    fun updateNote(note: Note)

    fun insertNote(note: Note)

    fun deleteNote(id: Long)

}