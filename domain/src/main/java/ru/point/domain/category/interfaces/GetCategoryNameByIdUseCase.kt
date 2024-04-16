package ru.point.domain.category.interfaces

import kotlinx.coroutines.flow.Flow

interface GetCategoryNameByIdUseCase {
    fun invoke(id: Long): Flow<String>
}