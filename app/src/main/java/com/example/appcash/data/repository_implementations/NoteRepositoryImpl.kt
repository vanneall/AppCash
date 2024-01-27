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
        return database.getNoteDao().getById(id = id)
    }

    override fun insertLinkedNote(note: Note, folderId: Long) {
        database.getNoteDao()
            .insertTransaction(
                note = note,
                folderId = folderId,
                insertNoteToFolderLink = database.getNoteToFolderLinkDao()::insertNotesToFolderLink
            )
    }

    override fun updateNote(note: Note) {
        database.getNoteDao().update(note = note)
    }

    override fun getNotes(): Flow<List<Note>> {
        return database.getNoteDao().get()
    }

    override fun insertNote(note: Note) {
        database.getNoteDao().insert(note = note)
    }

    override fun deleteNote(id: Long) {
        database.getNoteDao().delete(id = id)
    }
}