package com.example.appcash.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appcash.data.entities.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note WHERE note.id = :id")
    fun getNoteById(id: Long): Flow<Note>

    @Insert
    fun insertNote(note: Note)
}