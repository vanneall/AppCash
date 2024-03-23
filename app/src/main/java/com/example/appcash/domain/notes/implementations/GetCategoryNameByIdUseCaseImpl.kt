package com.example.appcash.domain.notes.implementations

import com.example.appcash.data.repository_interfaces.CategoriesRepository
import com.example.appcash.domain.notes.interfaces.GetCategoryNameByIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryNameByIdUseCaseImpl @Inject constructor(
    private val repository: CategoriesRepository
): GetCategoryNameByIdUseCase {
    override fun invoke(id: Long): Flow<String> {
        return repository.getCategoryNameById(id = id)
    }
}