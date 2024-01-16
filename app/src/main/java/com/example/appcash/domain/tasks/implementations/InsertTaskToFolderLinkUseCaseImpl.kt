package com.example.appcash.domain.tasks.implementations

import com.example.appcash.data.entities.TaskToFolderLink
import com.example.appcash.data.repository_interfaces.TaskToFolderRepository
import com.example.appcash.domain.tasks.interfaces.InsertTaskToFolderLinkUseCase
import javax.inject.Inject

class InsertTaskToFolderLinkUseCaseImpl @Inject constructor(
    private val repository: TaskToFolderRepository
): InsertTaskToFolderLinkUseCase {
    override fun invoke(taskToFolderLink: TaskToFolderLink) {
        repository.insertTaskToFolderLink(taskToFolderLink)
    }
}