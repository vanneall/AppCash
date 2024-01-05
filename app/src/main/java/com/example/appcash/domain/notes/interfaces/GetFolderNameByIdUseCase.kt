package com.example.appcash.domain.notes.interfaces

import kotlinx.coroutines.flow.Flow

interface GetFolderNameByIdUseCase {
    fun invoke(id: Long): Flow<String>
}