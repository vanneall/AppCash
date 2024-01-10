package com.example.appcash.data.repository_implementations

import com.example.appcash.data.databases.FoldersDatabase
import com.example.appcash.data.entities.Note
import com.example.appcash.data.repository_interfaces.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val database: FoldersDatabase
) : NoteRepository {
    override fun getNoteById(id: Long): Flow<Note> {
        return database.getNoteDao().getNoteById(id = id)
    }

    override fun insertNote(note: Note, folderId: Long) {
        database.getNoteDao()
            .insertTransaction(
                note = note,
                folderId = folderId,
                insertNoteToFolderLink = database.getNoteToFolderLinkDao()::insertNotesToFolderLink
            )
    }

    override fun updateNote(note: Note) {
        database.getNoteDao().updateNote(note = note)
    }

    override fun getNotes(): Flow<List<Note>> {
        return database.getNoteDao().getNotes()
    }
}