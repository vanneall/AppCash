package ru.point.data.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entities.Task
import ru.point.data.data.entities.TaskWithTask
import java.time.LocalDate

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(task: Task): Long

    @Transaction
    @Query("SELECT id, text, isCompleted, is_bookmark, description, date from task where parent_id is null ")
    fun readAll(): Flow<List<TaskWithTask>>

    @Transaction
    @Query("SELECT id, text, isCompleted, is_bookmark, description, date from task where parent_id is null and category_id = :id ")
    fun readByFolderId(id: Long): Flow<List<TaskWithTask>>

    @Transaction
    @Query("SELECT id, text, isCompleted, is_bookmark, description, date FROM task WHERE :id = id")
    fun readById(id: Long): Flow<List<TaskWithTask>>

    @Query("UPDATE task SET isCompleted = :isChecked WHERE id = :id")
    fun update(id: Long, isChecked: Boolean)

    @Query("SELECT COUNT(id) FROM task ")
    fun getAllTasksCount(): Flow<Int>

    @Query("SELECT COUNT(id) FROM task WHERE is_bookmark = 1")
    fun getBookmarksCount(): Flow<Int>

    @Query("DELETE FROM task WHERE id = :id ")
    fun deleteById(id: Long)

    @Query("UPDATE task set is_bookmark = not is_bookmark where id = :id ")
    fun updateBookmark(id: Long)

    @Transaction
    @Query("SELECT id, text, isCompleted, is_bookmark, description, date from task where parent_id is null and is_bookmark = 1 ")
    fun getBookmarksTasks(): Flow<List<TaskWithTask>>

    @Query("UPDATE task set text = :name, description = :description, date = :date where id = :id ")
    fun updateTask(id: Long, name: String, description: String, date: LocalDate)
}