package com.example.appcash.data.repository_implementations

import com.example.appcash.data.databases.FoldersDatabase
import com.example.appcash.data.entities.TaskToFolderLink
import com.example.appcash.data.repository_interfaces.TaskToFolderRepository
import javax.inject.Inject

class TaskToFolderRepositoryImpl @Inject constructor(
    private val database: FoldersDatabase
): TaskToFolderRepository {
    override fun insertTaskToFolderLink(taskToFolderLink: TaskToFolderLink) {
        database.getTaskToFolderDao().insertTaskToFolderLink(taskToFolderLink)
    }
}