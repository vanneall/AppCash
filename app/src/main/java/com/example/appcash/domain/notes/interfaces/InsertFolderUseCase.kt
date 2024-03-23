package com.example.appcash.domain.notes.interfaces

import com.example.appcash.data.entities.Category.Discriminator

interface InsertFolderUseCase {
    operator fun invoke(
        name: String,
        colorIndex: Int,
        discriminator: Discriminator,
        iconId: String
    )
}