package com.example.appcash.data.repository_implementations

import com.example.appcash.data.dao.TaskDao
import com.example.appcash.data.entities.Task
import com.example.appcash.data.entities.TaskWithTask
import com.example.appcash.data.repository_interfaces.TasksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TasksRepository {

    override fun createTask(task: Task) {
        taskDao.create(task = task)
    }

    override fun getTasks(): Flow<List<TaskWithTask>> {
        return taskDao.readAll()
    }

    override fun getTasks(folderId: Long): Flow<List<TaskWithTask>> {
        return taskDao.readByFolderId(id = folderId)
    }

    override fun updateTask(id: Long, isChecked: Boolean) {
        taskDao.update(id = id, isChecked = isChecked)
    }

    override fun getCompletedCount(): Flow<Int> {
        return taskDao.getCompletedCount()
    }

    override fun getPlannedCount(): Flow<Int> {
        return taskDao.getPlannedCount()
    }
}