package com.example.appcash.domain.notes.implementations

import com.example.appcash.data.entities.Category
import com.example.appcash.data.entities.Category.Discriminator
import com.example.appcash.data.repository_interfaces.CategoriesRepository
import com.example.appcash.domain.notes.interfaces.InsertFolderUseCase
import javax.inject.Inject

class InsertFolderUseCaseImpl @Inject constructor(
    private val repository: CategoriesRepository
) : InsertFolderUseCase {
    override fun invoke(name: String, colorIndex: Int, discriminator: Discriminator, iconId: String) {
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