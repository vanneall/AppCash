package ru.point.data.data.factory

import ru.point.data.data.entity.entities.Category
import ru.point.data.data.entity.entities.FolderIcon

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