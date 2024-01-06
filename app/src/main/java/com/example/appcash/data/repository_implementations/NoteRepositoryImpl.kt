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
}