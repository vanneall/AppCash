package com.example.appcash.data.repository_implementations

import com.example.appcash.data.databases.FoldersDatabase
import com.example.appcash.data.entities.Task
import com.example.appcash.data.repository_interfaces.TasksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val database: FoldersDatabase
): TasksRepository {
    override fun getTasks(): Flow<Task> {
        return database.getTasksDao().getTasks()
    }

    override fun getTasksById(id: Long): Flow<Task> {
        return database.getTasksDao().getTasksById(id = id)
    }

    override fun getCompletedCount(): Flow<Int> {
        return database.getTasksDao().getCompletedCount()
    }

    override fun getPlannedCount(): Flow<Int> {
        return database.getTasksDao().getPlannedCount()
    }
}