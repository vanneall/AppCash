package ru.point.data.data.factory

import ru.point.data.data.entities.Category
import ru.point.data.data.entities.FolderIcon

interface CategoryFactory {
    fun create(
        name: String,
        color: Int,
        discriminator: Category.Discriminator,
        icon: FolderIcon
    ): Category
}