package com.example.appcash.data.repository_interfaces

import com.example.appcash.data.entities.TaskToFolderLink

interface TaskToFolderRepository {
    fun insertTaskToFolderLink(taskToFolderLink: TaskToFolderLink)
}