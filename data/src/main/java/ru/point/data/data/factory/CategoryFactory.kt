package ru.point.data.data.factory

import ru.point.data.data.entity.entities.Category
import ru.point.data.data.entity.entities.FolderIcon

interface CategoryFactory {
    fun create(
        name: String,
        color: Int,
        discriminator: Category.Discriminator,
        icon: FolderIcon
    ): Category
}