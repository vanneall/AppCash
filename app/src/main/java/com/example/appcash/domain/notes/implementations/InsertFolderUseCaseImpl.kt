package com.example.appcash.domain.notes.implementations

import android.util.Log
import com.example.appcash.data.entities.Category
import com.example.appcash.data.entities.Category.Discriminator
import com.example.appcash.data.repository_interfaces.CategoriesRepository
import com.example.appcash.domain.notes.interfaces.InsertFolderUseCase
import com.example.appcash.utils.events.Event.ErrorEvent
import javax.inject.Inject

class InsertFolderUseCaseImpl @Inject constructor(
    private val repository: CategoriesRepository
) : InsertFolderUseCase {
    override fun invoke(name: String, colorIndex: Int, discriminator: Discriminator, iconId: String, onError: (ErrorEvent) -> Unit) {
        val nameTrimmed = name.trim()

        if (nameTrimmed.isEmpty()) return

        try {
            repository.insertCategory(
                category = Category(
                    name = nameTrimmed,
                    colorIndex = colorIndex,
                    discriminator = discriminator,
                    icon = "car_folder_icon"
                )
            )
        } catch (ex: Exception) {
            onError(ErrorEvent)

            Log.e("Insert exception", ex.stackTrace.contentToString())
        }
    }
}