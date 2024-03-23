package com.example.appcash.domain.notes.interfaces

import com.example.appcash.data.entities.Category
import com.example.appcash.data.entities.Category.Discriminator
import kotlinx.coroutines.flow.Flow

interface GetCategoryByTypeUseCase {
    fun invoke(type: Discriminator): Flow<List<Category>>
}