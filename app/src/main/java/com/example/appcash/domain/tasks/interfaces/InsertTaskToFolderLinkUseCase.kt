package com.example.appcash.domain.tasks.interfaces

import com.example.appcash.data.entities.TaskToFolderLink

interface InsertTaskToFolderLinkUseCase {
    fun invoke(taskToFolderLink: TaskToFolderLink)
}