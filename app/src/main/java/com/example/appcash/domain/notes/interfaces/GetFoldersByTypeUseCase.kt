package com.example.appcash.domain.notes.interfaces

import com.example.appcash.data.entities.Category
import com.example.appcash.data.entities.Category.Discriminator
import com.example.appcash.utils.events.Event.ErrorEvent
import kotlinx.coroutines.flow.Flow

interface GetFoldersByTypeUseCase {
    fun invoke(type: Discriminator, onError: (ErrorEvent) -> Unit): Flow<List<Category>>
}