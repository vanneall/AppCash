package com.example.appcash.data.repository_interfaces

import com.example.appcash.data.entities.Task
import com.example.appcash.data.entities.TaskWithTask
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun createTask(task: Task)
    fun getCompletedCount(): Flow<Int>
    fun getPlannedCount(): Flow<Int>
    fun getTasks(): Flow<List<TaskWithTask>>
    fun getTasks(folderId: Long): Flow<List<TaskWithTask>>
    fun updateTask(id: Long, isChecked: Boolean)
}