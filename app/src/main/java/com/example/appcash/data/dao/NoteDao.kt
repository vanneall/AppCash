package com.example.appcash.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.appcash.data.entities.Note
import com.example.appcash.data.entities.NoteToFolderLink
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note WHERE note.id = :id")
    fun getById(id: Long): Flow<Note>

    @Query("SELECT * FROM note")
    fun get(): Flow<List<Note>>

    @Update
    fun update(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note): Long

    @Transaction
    fun insertTransaction(
        note: Note,
        folderId: Long,
        insertNoteToFolderLink: (NoteToFolderLink) -> Unit
    ) {
        val noteId = insert(note = note)
        insertNoteToFolderLink(
            NoteToFolderLink(
                folderId = folderId,
                noteId = noteId
            )
        )
    }

    @Query("DELETE FROM note WHERE id = :id")
    fun delete(id: Long)
}