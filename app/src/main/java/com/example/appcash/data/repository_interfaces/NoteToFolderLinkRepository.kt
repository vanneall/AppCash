package com.example.appcash.data.repository_interfaces

import com.example.appcash.data.entities.Note
import com.example.appcash.data.entities.NoteToFolderLink
import kotlinx.coroutines.flow.Flow

interface NoteToFolderLinkRepository {

    fun getNotesByFolderId(id: Long): Flow<List<Note>>
}