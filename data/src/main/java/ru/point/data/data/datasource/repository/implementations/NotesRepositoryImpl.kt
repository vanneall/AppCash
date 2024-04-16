package ru.point.data.data.datasource.repository.implementations

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.point.data.data.datasource.local.dao.NoteDao
import ru.point.data.data.entity.entities.Note
import ru.point.data.data.datasource.repository.interfaces.NotesRepository
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NotesRepository {
    override suspend fun upsert(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.upsert(value = note)
        }
    }

    override suspend fun getNoteById(id: Long): Flow<Note> {
        return withContext(Dispatchers.IO) {
            noteDao.readById(id = id)
        }
    }

    override suspend fun getAllNotes(): Flow<List<Note>> {
        return withContext(Dispatchers.IO) {
            noteDao.readAll()
        }
    }

    override suspend fun getNotesByFolderId(folderId: Long): Flow<List<Note>> {
        return withContext(Dispatchers.IO) {
            noteDao.readAllByFolderId(folderId)
        }
    }

    override suspend fun delete(id: Long) {
        withContext(Dispatchers.IO) {
            noteDao.delete(id = id)
        }
    }

    override suspend fun getNoteCount(): Flow<Int> {
        return withContext(Dispatchers.IO) {
            noteDao.getCount()
        }
    }
}