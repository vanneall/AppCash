package com.example.appcash.domain.notes.implementations

import com.example.appcash.data.Folder
import com.example.appcash.data.repository_interfaces.FoldersRepository
import com.example.appcash.domain.notes.interfaces.InsertFolderUseCase

class InsertFolderUseCaseImpl(
    private val repository: FoldersRepository
): InsertFolderUseCase {
    override fun invoke(folder: Folder) {
        repository.insertFolder(folder = folder)
    }
}