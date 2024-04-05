package com.example.appcash.view.popup.taskconfigurator

data class TaskConfiguratorPopupState(
    val id: Long? = null,
    val isShowed: Boolean = false,
    val name: String = "",
    val description: String = "",
    val folderId: Long? = null,
    val parentId: Long? = null,
    val date: String = ""
)