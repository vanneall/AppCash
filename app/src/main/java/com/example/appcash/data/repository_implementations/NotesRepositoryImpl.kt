package com.example.appcash.data.repository_implementations

import com.example.appcash.data.dao.NoteDao
import com.example.appcash.data.entities.Note
import com.example.appcash.data.repository_interfaces.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NotesRepository {
    override fun createNote(note: Note) {
        noteDao.create(value = note)
    }

    override fun getNoteById(id: Long): Flow<Note> {
        return noteDao.readById(id = id)
    }

    override fun getNotes(): Flow<List<Note>> {
        return noteDao.readAll()
    }

    override fun getNotes(folderId: Long): Flow<List<Note>> {
        return noteDao.readAllByFolderId(folderId)
    }

    override fun updateNote(note: Note) {
        noteDao.update(value = note)
    }

    override fun deleteNote(id: Long) {
        noteDao.delete(id = id)
    }
}