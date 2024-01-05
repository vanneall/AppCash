package com.example.appcash.data.repository_implementations

import com.example.appcash.data.databases.FoldersDatabase
import com.example.appcash.data.entities.Note
import com.example.appcash.data.entities.NoteToFolderLink
import com.example.appcash.data.repository_interfaces.NoteToFolderLinkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteToFolderLinkRepositoryImpl @Inject constructor(
    private val database: FoldersDatabase
): NoteToFolderLinkRepository {
    override fun getNotesByFolderId(id: Long): Flow<List<Note>> {
        return database.getNoteToFolderLinkDao().getNotesByFolderId(id = id)
    }
}