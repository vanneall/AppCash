package ru.point.domain.notes.interfaces

import ru.point.data.data.entities.Category

interface InsertFolderUseCase {
    operator fun invoke(
        name: String,
        colorIndex: Int,
        discriminator: Category.Discriminator,
        iconId: String
    )
}