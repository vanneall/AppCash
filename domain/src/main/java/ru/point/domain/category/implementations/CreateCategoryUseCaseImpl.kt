package ru.point.domain.category.implementations

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.point.data.data.entity.entities.Category
import ru.point.data.data.entity.entities.FolderIcon
import ru.point.data.data.factory.CategoryFactory
import ru.point.data.data.datasource.repository.interfaces.CategoriesRepository
import ru.point.domain.category.interfaces.CreateCategoryUseCase
import javax.inject.Inject

class CreateCategoryUseCaseImpl @Inject constructor(
    private val repository: CategoriesRepository,
    private val factory: CategoryFactory
) : CreateCategoryUseCase {
    override fun invoke(
        name: String,
        colorIndex: Int,
        discriminator: Category.Discriminator,
        iconId: FolderIcon
    ) {
        CoroutineScope(Dispatchers.Default).launch {
            val nameTrimmed = name.trim()
            if (nameTrimmed.isEmpty()) return@launch

            val category = factory.create(
                name = name, color = colorIndex,
                discriminator = discriminator,
                icon = iconId
            )

            repository.insertCategory(category)
        }
    }
}