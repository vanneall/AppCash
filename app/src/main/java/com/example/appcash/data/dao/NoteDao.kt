package com.example.appcash.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.example.appcash.data.entities.Note
import com.example.appcash.data.entities.NoteToFolderLink
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note WHERE note.id = :id")
    fun getNoteById(id: Long): Flow<Note>

    @Update
    fun updateNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note): Long

    @Transaction
    fun insertTransaction(
        note: Note,
        folderId: Long,
        insertNoteToFolderLink: (NoteToFolderLink) -> Unit
    ) {
        val noteId = insertNote(note = note)
        insertNoteToFolderLink(
            NoteToFolderLink(
                folderId = folderId,
                noteId = noteId
            )
        )
    }
}