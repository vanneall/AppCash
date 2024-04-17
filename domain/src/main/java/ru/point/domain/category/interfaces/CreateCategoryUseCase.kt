package ru.point.domain.category.interfaces

import ru.point.data.data.entity.entities.Category
import ru.point.data.data.entity.entities.FolderIcon

interface CreateCategoryUseCase {
    operator fun invoke(
        name: String,
        color: Int,
        discriminator: Category.Discriminator,
        iconId: FolderIcon
    )
}