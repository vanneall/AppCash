package ru.point.data.data.datasource.repository.interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entity.entities.Task
import ru.point.data.data.entity.entities.TaskWithTask
import java.time.LocalDate

interface TasksRepository {
    suspend fun createTask(task: Task)
    suspend fun getAllTasksCount(): Flow<Int>
    suspend fun getBookmarksCount(): Flow<Int>
    suspend fun getTasks(): Flow<List<TaskWithTask>>
    suspend fun getTasks(folderId: Long): Flow<List<TaskWithTask>>
    suspend fun updateTaskChecked(id: Long, isChecked: Boolean)
    suspend fun deleteTaskById(id: Long)
    suspend fun getBookmarkTasks(): Flow<List<TaskWithTask>>
    suspend fun updateBookmarkTasks(id: Long)
    suspend fun updateTask(id: Long, name: String, description: String, date: LocalDate)
}