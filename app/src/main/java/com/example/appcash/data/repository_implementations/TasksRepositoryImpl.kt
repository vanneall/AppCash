package com.example.appcash.data.repository_implementations

import com.example.appcash.data.databases.FoldersDatabase
import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.entities.SubTask
import com.example.appcash.data.entities.Task
import com.example.appcash.data.entities.TaskToFolderLink
import com.example.appcash.data.repository_interfaces.TasksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val database: FoldersDatabase
) : TasksRepository {
    override fun getTasks(): Flow<MainTask> {
        return database.getTasksDao().getTasks()
    }

    override fun getTasksById(id: Long): Flow<MainTask> {
        return database.getTasksDao().getTasksById(id = id)
    }

    override fun getCompletedCount(): Flow<Int> {
        return database.getTasksDao().getCompletedCount()
    }

    override fun getPlannedCount(): Flow<Int> {
        return database.getTasksDao().getPlannedCount()
    }

    override fun getAllTasks(): Flow<Map<MainTask, List<SubTask>?>> {
        return database.getTasksDao().getAllTasks()
    }

    override fun insertSubTask(mainId: Long, subTask: SubTask) {
        database.getTasksDao().insertSubTaskTransaction(
            mainTaskId = mainId,
            subTask = subTask,
            insertSubToMainLink = database.getSubTaskToMainTaskDao()::insertSubToMainLink
        )
    }

    override fun insertMainTransaction(
        mainId: Long,
        mainTask: MainTask,
    ) {
        database.getTasksDao().insertMainTransaction(
            mainTask,
            mainId,
            database.getTaskToFolderDao()::insertTaskToFolderLink
        )
    }

    override fun insertMainTask(mainTask: MainTask) {
        database.getTasksDao().insertMainTask(mainTask = mainTask)
    }

    override fun updateTask(task: Task) {
        when (task) {
            is MainTask -> database.getTasksDao()
                .updateMainTask(task.copy(isCompleted = !task.isCompleted))

            is SubTask -> database.getTasksDao()
                .updateSubTask(task.copy(isCompleted = !task.isCompleted))
        }
    }

    override fun getAllTasksById(id: Long): Flow<Map<MainTask, List<SubTask>?>> {
        return database.getTasksDao().getAllTasksByFolderId(id = id)
    }
}