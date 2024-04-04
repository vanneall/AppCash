package ru.point.domain.notes.interfaces

import ru.point.data.data.entities.Category
import ru.point.data.data.entities.FolderIcon

interface InsertFolderUseCase {
    operator fun invoke(
        name: String,
        colorIndex: Int,
        discriminator: Category.Discriminator,
        iconId: FolderIcon
    )
}