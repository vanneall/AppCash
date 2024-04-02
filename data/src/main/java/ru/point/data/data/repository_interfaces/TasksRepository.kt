package ru.point.data.data.repository_interfaces

import ru.point.data.data.entities.Task
import ru.point.data.data.entities.TaskWithTask
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun createTask(task: Task)
    fun getCompletedCount(): Flow<Int>
    fun getPlannedCount(): Flow<Int>
    fun getTasks(): Flow<List<TaskWithTask>>
    fun getTasks(folderId: Long): Flow<List<TaskWithTask>>
    fun updateTask(id: Long, isChecked: Boolean)
}