package com.example.appcash.view.popup

data class EditPopupState(
    val isShowed: Boolean = false,
    val folderId: Long? = null,
    val parentId: Long? = null,
    val name: String = "",
    val description: String = "",
    val date: String = ""
)