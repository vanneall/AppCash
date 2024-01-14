package com.example.appcash.data.repository_interfaces

import androidx.room.Query
import com.example.appcash.data.entities.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    fun getTasks(): Flow<Task>

    fun getTasksById(id: Long): Flow<Task>

    fun getCompletedCount(): Flow<Int>

    fun getPlannedCount(): Flow<Int>

}