package com.example.appcash.domain.notes.interfaces

import com.example.appcash.data.entities.Folder
import kotlinx.coroutines.flow.Flow

interface GetFoldersUseCase {
    fun invoke(): Flow<List<Folder>>
}