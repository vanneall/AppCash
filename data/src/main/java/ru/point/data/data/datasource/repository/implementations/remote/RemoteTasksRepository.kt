package ru.point.data.data.datasource.repository.implementations.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.withContext
import ru.point.data.data.datasource.remote.api.TaskApi
import ru.point.data.data.datasource.remote.api.TaskDto
import ru.point.data.data.datasource.repository.interfaces.TasksRepository
import ru.point.data.data.entity.entities.Task
import ru.point.data.data.entity.entities.TaskWithTask
import java.time.LocalDate
import javax.inject.Inject

class RemoteTasksRepository @Inject constructor(
    private val api: TaskApi
) : TasksRepository {
    override suspend fun createTask(task: Task) {
        withContext(Dispatchers.IO) {
            val a = TaskDto(
                task.text,
                task.description,
                isCompleted = false,
                taskId = task.parentTaskId,
                folderId = task.categoryId,
                isFavorite = false,
                date = LocalDate.now().toString()
            )
            api.createTask(a).execute()
        }
    }

    override suspend fun getAllTasksCount(): Flow<Int> {
        return channelFlow {
            withContext(Dispatchers.IO) {
                val a = api.getAllTasks().execute().body() ?: return@withContext
                send(a.size)
            }
        }
    }

    override suspend fun getBookmarksCount(): Flow<Int> {
        return channelFlow {
            withContext(Dispatchers.IO) {
                val a = api.getAllTasks().execute().body() ?: return@withContext
                send(a.count { it.isBookmarked })
            }
        }
    }

    override suspend fun getTasks(): Flow<List<TaskWithTask>> {
        return channelFlow {
            withContext(Dispatchers.IO) {
                val a = api.getAllTasks().execute().body() ?: return@withContext
                send(a)
            }
        }
    }

    override suspend fun getTasks(folderId: Long): Flow<List<TaskWithTask>> {
        return channelFlow {
            withContext(Dispatchers.IO) {
                val a = api.getTasksByFolderId(folderId = folderId).execute().body()
                    ?: return@withContext
                a.forEach {
                    it.date = it.date.substringBefore("T")
                }
                send(a)
            }
        }
    }

    override suspend fun updateTaskChecked(id: Long, isChecked: Boolean) {
        withContext(Dispatchers.IO) {
            val a = api.getAllTasks().execute().body() ?: return@withContext
            val dto = a.find { it.id == id || it?.subtasks?.any { el -> el.id == id } ?: false }
                ?: return@withContext


            if (dto.id == id) {
                dto.isCompleted = !dto.isCompleted
                api.updateCheckedTask(dto).execute()
            } else {
                val res = dto.subtasks.find { it.id == id } ?: return@withContext
                res.isCompleted = isChecked
                api.updateCheckedTask(res).execute()
            }
        }
    }

    override suspend fun deleteTaskById(id: Long) {
        withContext(Dispatchers.IO) {
            api.deleteTaskById(id).execute()
        }
    }

    override suspend fun getBookmarkTasks(): Flow<List<TaskWithTask>> {
        return channelFlow {
            withContext(Dispatchers.IO) {
                val a = api.getAllTasks().execute().body() ?: return@withContext
                send(a.filter { it.isBookmarked })
            }
        }
    }

    override suspend fun updateBookmarkTasks(id: Long) {
        withContext(Dispatchers.IO) {
            val a = api.getAllTasks().execute().body() ?: return@withContext
            val dto = a.find { it.id == id } ?: return@withContext
            dto.isBookmarked = !dto.isBookmarked
            api.updateCheckedTask(dto).execute()
        }
    }

    override suspend fun updateTask(id: Long, name: String, description: String, date: LocalDate) {
        withContext(Dispatchers.IO) {
            val a = api.getAllTasks().execute().body() ?: return@withContext
            val dto = a.find { it.id == id } ?: return@withContext
            api.updateCheckedTask(
                dto.copy(
                    text = name,
                    description = description,
                    date = date.toString()
                )
            ).execute()
        }
    }
}