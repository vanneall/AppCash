package ru.point.data.data.repository_interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entities.Task
import ru.point.data.data.entities.TaskWithTask

interface TasksRepository {
    fun createTask(task: Task)
    fun getAllTasksCount(): Flow<Int>
    fun getPlannedCount(): Flow<Int>
    fun getTasks(): Flow<List<TaskWithTask>>
    fun getTasks(folderId: Long): Flow<List<TaskWithTask>>
    fun updateTask(id: Long, isChecked: Boolean)
    fun deleteTaskById(id: Long)
}