package com.example.appcash.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.appcash.data.entities.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(value: Note): Long

    @Query("SELECT * FROM note")
    fun readAll(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE note.id = :id")
    fun readById(id: Long): Flow<Note>

    @Query("SELECT * FROM note WHERE note.category_id = :id ")
    fun readAllByFolderId(id: Long): Flow<List<Note>>

    @Update
    fun update(value: Note)

    @Query("DELETE FROM note WHERE id = :id")
    fun delete(id: Long)
}