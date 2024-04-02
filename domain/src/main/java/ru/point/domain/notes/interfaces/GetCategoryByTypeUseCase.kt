package ru.point.domain.notes.interfaces

import kotlinx.coroutines.flow.Flow
import ru.point.data.data.entities.Category

interface GetCategoryByTypeUseCase {
    fun invoke(type: Category.Discriminator): Flow<List<Category>>
}