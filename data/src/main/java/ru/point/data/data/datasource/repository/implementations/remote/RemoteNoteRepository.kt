package ru.point.data.data.datasource.repository.implementations.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import ru.point.data.data.datasource.remote.api.NoteApi
import ru.point.data.data.datasource.repository.interfaces.NotesRepository
import ru.point.data.data.entity.dto.toNoteDto
import ru.point.data.data.entity.entities.Note
import javax.inject.Inject

class RemoteNoteRepository @Inject constructor(
    private val api: NoteApi
) : NotesRepository {
    override suspend fun getNoteById(id: Long): Flow<Note> {
       return channelFlow {
           withContext(Dispatchers.IO) {

           }
       }
    }

    override suspend fun getAllNotes(): Flow<List<Note>> {
        return channelFlow {
            withContext(Dispatchers.IO) {
                send(api.getNotes())
            }
        }
    }

    override suspend fun getNotesByFolderId(folderId: Long): Flow<List<Note>> {
        return channelFlow {
            withContext(Dispatchers.IO) {
                send(api.getNotesByFolderId(folderId = folderId.toInt()))
            }
        }
    }

    override suspend fun getNoteCount(): Flow<Int> {
        return flowOf(1)
    }

    override suspend fun upsert(note: Note) {
        withContext(Dispatchers.IO) {
            val noteDto = note.toNoteDto()
            println(noteDto)
            api.createNote(noteDto)
        }
    }

    override suspend fun delete(id: Long) {
        TODO("Not yet implemented")
    }
}