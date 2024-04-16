package ru.point.data.data.datasource.repository.implementations

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.point.data.data.datasource.local.dao.TaskDao
import ru.point.data.data.entity.entities.Task
import ru.point.data.data.entity.entities.TaskWithTask
import ru.point.data.data.datasource.repository.interfaces.TasksRepository
import java.time.LocalDate
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TasksRepository {

    override suspend fun createTask(task: Task) {
        withContext(Dispatchers.IO) {
            taskDao.create(task = task)
        }
    }

    override suspend fun getTasks(): Flow<List<TaskWithTask>> {
        return withContext(Dispatchers.IO) {
            taskDao.readAll()
        }
    }

    override suspend fun getTasks(folderId: Long): Flow<List<TaskWithTask>> {
        return withContext(Dispatchers.IO) {
            taskDao.readByFolderId(id = folderId)
        }
    }

    override suspend fun updateTaskChecked(id: Long, isChecked: Boolean) {
        withContext(Dispatchers.IO) {
            taskDao.update(id = id, isChecked = isChecked)
        }
    }

    override suspend fun getAllTasksCount(): Flow<Int> {
        return withContext(Dispatchers.IO) {
            taskDao.getAllTasksCount()
        }
    }

    override suspend fun getBookmarksCount(): Flow<Int> {
        return withContext(Dispatchers.IO) {
            taskDao.getBookmarksCount()
        }
    }

    override suspend fun deleteTaskById(id: Long) {
        withContext(Dispatchers.IO) {
            taskDao.deleteById(id = id)
        }
    }

    override suspend fun getBookmarkTasks(): Flow<List<TaskWithTask>> {
        return withContext(Dispatchers.IO) {
            taskDao.getBookmarksTasks()
        }
    }

    override suspend fun updateBookmarkTasks(id: Long) {
        withContext(Dispatchers.IO) {
            taskDao.updateBookmark(id = id)
        }
    }

    override suspend fun updateTask(id: Long, name: String, description: String, date: LocalDate) {
        withContext(Dispatchers.IO) {
            taskDao.updateTask(id = id, name = name, description = description, date = date)
        }
    }
}