package com.example.appcash.view.popup.create

import ru.point.data.data.entities.FolderIcon

data class CreateCategoryPopupState(
    val name: String = "",
    val selectedFolderIcon: FolderIcon = FolderIcon.UNKNOWN,
    val isShowed: Boolean = false
)