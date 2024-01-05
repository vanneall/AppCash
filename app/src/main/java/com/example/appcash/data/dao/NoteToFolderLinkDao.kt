package com.example.appcash.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.appcash.data.entities.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteToFolderLinkDao {
    @Query("SELECT note.id, note.title, note.content FROM note JOIN notetofolderlink ON notetofolderlink.folderId = :id and notetofolderlink.noteId = note.id")
    fun getNotesByFolderId(id: Long): Flow<List<Note>>

}