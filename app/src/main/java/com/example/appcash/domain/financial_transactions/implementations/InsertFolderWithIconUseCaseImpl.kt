package com.example.appcash.domain.financial_transactions.implementations

import com.example.appcash.data.entities.Folder
import com.example.appcash.data.repository_interfaces.FoldersRepository
import com.example.appcash.domain.financial_transactions.interfaces.InsertFolderWithIconUseCase
import javax.inject.Inject

class InsertFolderWithIconUseCaseImpl @Inject constructor(
    private val repository: FoldersRepository
): InsertFolderWithIconUseCase {
    override fun invoke(folder: Folder, iconId: String) {
        repository.insertFolderWithIcon(folder, iconId)
    }
}