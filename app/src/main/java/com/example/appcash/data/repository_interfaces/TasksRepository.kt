package com.example.appcash.data.repository_interfaces

import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.entities.SubTask
import com.example.appcash.data.entities.Task
import com.example.appcash.data.entities.TaskToFolderLink
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    fun getTasks(): Flow<MainTask>

    fun getTasksById(id: Long): Flow<MainTask>

    fun getCompletedCount(): Flow<Int>

    fun getPlannedCount(): Flow<Int>

    fun getAllTasks(): Flow<Map<MainTask, List<SubTask>?>>

    fun getAllTasksById(id: Long): Flow<Map<MainTask, List<SubTask>?>>

    fun insertSubTask(mainId: Long, subTask: SubTask)

    fun insertMainTask(mainTask: MainTask)

    fun insertMainTransaction(mainId: Long, mainTask: MainTask)

    fun updateTask(task: Task)

}