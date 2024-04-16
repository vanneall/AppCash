package ru.point.domain.category.interfaces

import ru.point.data.data.entities.Category
import ru.point.data.data.entities.FolderIcon

interface CreateCategoryUseCase {
    operator fun invoke(
        name: String,
        colorIndex: Int,
        discriminator: Category.Discriminator,
        iconId: FolderIcon
    )
}