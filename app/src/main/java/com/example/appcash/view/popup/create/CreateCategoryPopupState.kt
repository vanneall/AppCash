package com.example.appcash.view.popup.create

import androidx.compose.ui.graphics.toArgb
import com.example.appcash.utils.ParamsStore
import ru.point.data.data.entity.entities.FolderIcon

data class CreateCategoryPopupState(
    val name: String = "",
    val selectedFolderIcon: FolderIcon = FolderIcon.CAR,
    val selectedColor: Int = ParamsStore.colorsList[0].toArgb(),
    val isNameTextFieldError: Boolean = false,
    val isShowed: Boolean = false,
    val selectedIconIndex: Int = 0,
    val selectedColorIndex: Int = 0,
)