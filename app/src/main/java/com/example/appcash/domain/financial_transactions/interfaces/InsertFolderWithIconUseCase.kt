package com.example.appcash.domain.financial_transactions.interfaces

import com.example.appcash.data.entities.Folder

interface InsertFolderWithIconUseCase {
    operator fun invoke(folder: Folder, iconId: String)
}