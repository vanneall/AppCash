package ru.point.data.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entities.Note

@Dao
interface NoteDao {
    @Upsert(entity = Note::class)
    suspend fun upsert(value: Note): Long

    @Query("SELECT * FROM note")
    fun readAll(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE note.id = :id")
    fun readById(id: Long): Flow<Note>

    @Query("SELECT * FROM note WHERE note.category_id = :id ")
    fun readAllByFolderId(id: Long): Flow<List<Note>>

    @Query("DELETE FROM note WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("SELECT COUNT(id) FROM category")
    fun getCount(): Flow<Int>
}