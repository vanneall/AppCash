package com.example.appcash.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.appcash.data.entities.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Query("SELECT * FROM task")
    fun getTasks(): Flow<Task>

    @Query("SELECT * FROM task WHERE :id = id")
    fun getTasksById(id: Long): Flow<Task>

    @Query("SELECT COUNT(id) FROM task WHERE isCompleted = 1")
    fun getCompletedCount(): Flow<Int>

    @Query("SELECT COUNT(id) FROM task WHERE isCompleted = 0")
    fun getPlannedCount(): Flow<Int>

}