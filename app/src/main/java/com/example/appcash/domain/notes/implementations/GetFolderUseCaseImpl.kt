package com.example.appcash.domain.notes.implementations

import com.example.appcash.data.Folder
import com.example.appcash.domain.notes.interfaces.GetFoldersUseCase
import com.example.appcash.data.repository_interfaces.FoldersRepository
import kotlinx.coroutines.flow.Flow

class GetFolderUseCaseImpl(
    private val repository: FoldersRepository
) : GetFoldersUseCase {
    override fun invoke(): Flow<List<Folder>> = repository.getFolders()
}