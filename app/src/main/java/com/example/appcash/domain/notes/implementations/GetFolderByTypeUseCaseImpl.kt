package com.example.appcash.domain.notes.implementations

import com.example.appcash.data.entities.Category
import com.example.appcash.data.entities.Category.Discriminator
import com.example.appcash.data.repository_interfaces.CategoriesRepository
import com.example.appcash.domain.notes.interfaces.GetCategoryByTypeUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFolderByTypeUseCaseImpl @Inject constructor(
    private val repository: CategoriesRepository
) : GetCategoryByTypeUseCase {
    override fun invoke(type: Discriminator): Flow<List<Category>> {
        return repository.getCategoryByType(type = type)
    }
}