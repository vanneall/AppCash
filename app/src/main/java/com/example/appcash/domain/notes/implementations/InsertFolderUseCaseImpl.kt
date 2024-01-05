package com.example.appcash.domain.notes.implementations

import com.example.appcash.data.entities.Folder
import com.example.appcash.data.repository_interfaces.FoldersRepository
import com.example.appcash.domain.notes.interfaces.InsertFolderUseCase
import javax.inject.Inject

class InsertFolderUseCaseImpl @Inject constructor(
    private val repository: FoldersRepository
): InsertFolderUseCase {
    override fun invoke(folder: Folder) {
        repository.insertFolder(folder = folder)
    }
}