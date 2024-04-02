package com.example.appcash.view.popup

import com.example.appcash.utils.events.Event

sealed class EditPopupEvent : Event {

    data class ShowPopup(
        val id: Long? = null,
        val parentId: Long? = null,
        val name: String? = null,
        val description: String? = null,
        val date: String? = null
    ) : EditPopupEvent()
    object HidePopup : EditPopupEvent()

    data class SelectFolder(val folderId: Long) : EditPopupEvent()

    data class InsertName(val name: String) : EditPopupEvent()

    data class InsertDescription(val name: String) : EditPopupEvent()

    data class SelectDate(val stringDate: String) : EditPopupEvent()

    data class CreateTask(val parentTaskId: Long?) : EditPopupEvent()
}