package ru.point.domain.notes.implementations

import ru.point.data.data.entities.Category
import ru.point.data.data.repository_interfaces.CategoriesRepository
import ru.point.domain.notes.interfaces.InsertFolderUseCase
import javax.inject.Inject

class InsertFolderUseCaseImpl @Inject constructor(
    private val repository: CategoriesRepository
) : InsertFolderUseCase {
    override fun invoke(name: String, colorIndex: Int, discriminator: Category.Discriminator, iconId: String) {
        val nameTrimmed = name.trim()

        if (nameTrimmed.isEmpty()) return

        repository.insertCategory(
            category = Category(
                name = nameTrimmed,
                colorIndex = colorIndex,
                discriminator = discriminator,
                icon = "car_folder_icon"
            )
        )
    }
}