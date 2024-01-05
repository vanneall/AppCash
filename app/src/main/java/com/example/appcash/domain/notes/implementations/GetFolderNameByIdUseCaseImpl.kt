package com.example.appcash.domain.notes.implementations

import com.example.appcash.data.repository_interfaces.FoldersRepository
import com.example.appcash.domain.notes.interfaces.GetFolderNameByIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFolderNameByIdUseCaseImpl @Inject constructor(
    private val repository: FoldersRepository
): GetFolderNameByIdUseCase {
    override fun invoke(id: Long): Flow<String> {
        return repository.getFolderNameById(id = id)
    }
}