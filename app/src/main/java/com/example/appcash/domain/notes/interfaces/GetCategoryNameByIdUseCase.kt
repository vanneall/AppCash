package com.example.appcash.domain.notes.interfaces

import kotlinx.coroutines.flow.Flow

interface GetCategoryNameByIdUseCase {
    fun invoke(id: Long): Flow<String>
}