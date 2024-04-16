package ru.point.data.data.factory

import ru.point.data.data.entities.Category
import ru.point.data.data.entities.FolderIcon

class CategoryFactoryImpl : CategoryFactory {
    override fun create(
        name: String,
        color: Int,
        discriminator: Category.Discriminator,
        icon: FolderIcon
    ): Category {
        return Category(name = name, color = color, discriminator = discriminator, icon = icon)
    }
}