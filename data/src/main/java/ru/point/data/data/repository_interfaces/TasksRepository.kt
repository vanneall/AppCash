package ru.point.data.data.repository_interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entities.Task
import ru.point.data.data.entities.TaskWithTask
import java.time.LocalDate

interface TasksRepository {
    fun createTask(task: Task)
    fun getAllTasksCount(): Flow<Int>
    fun getBookmarksCount(): Flow<Int>
    fun getTasks(): Flow<List<TaskWithTask>>
    fun getTasks(folderId: Long): Flow<List<TaskWithTask>>
    fun updateTaskChecked(id: Long, isChecked: Boolean)
    fun deleteTaskById(id: Long)
    fun getBookmarkTasks(): Flow<List<TaskWithTask>>
    fun updateBookmarkTasks(id: Long)
    fun updateTask(id: Long, name: String, description: String, date: LocalDate)
}