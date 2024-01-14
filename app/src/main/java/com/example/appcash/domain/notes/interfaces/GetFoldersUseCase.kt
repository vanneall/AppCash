package com.example.appcash.domain.notes.interfaces

import com.example.appcash.data.entities.Folder
import com.example.appcash.data.entities.FolderType
import kotlinx.coroutines.flow.Flow

interface GetFoldersUseCase {
    fun invoke(type: FolderType): Flow<List<Folder>>
}