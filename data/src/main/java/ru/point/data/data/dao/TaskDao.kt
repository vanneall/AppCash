package ru.point.data.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entities.Task
import ru.point.data.data.entities.TaskWithTask

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(task: Task): Long

    @Transaction
    @Query("SELECT id, text, isCompleted from task where parent_id is null ")
    fun readAll(): Flow<List<TaskWithTask>>

    @Transaction
    @Query("SELECT id, text, isCompleted from task where parent_id is null and category_id = :id ")
    fun readByFolderId(id: Long): Flow<List<TaskWithTask>>

    @Transaction
    @Query("SELECT id, text, isCompleted FROM task WHERE :id = id")
    fun readById(id: Long): Flow<List<TaskWithTask>>

    @Query("UPDATE task SET isCompleted = :isChecked WHERE id = :id")
    fun update(id: Long, isChecked: Boolean)

    @Query("SELECT COUNT(id) FROM task ")
    fun getAllTasksCount(): Flow<Int>

    @Query("SELECT COUNT(id) FROM task WHERE isCompleted = 0")
    fun getPlannedCount(): Flow<Int>

    @Query("DELETE FROM task WHERE id = :id ")
    fun deleteById(id: Long)
}