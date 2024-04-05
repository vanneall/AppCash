package com.example.appcash.view.popup.taskcontroll

import com.example.appcash.utils.events.Event

sealed class TaskControlPopupEvent : Event {

    data class DeleteTask(val id: Long): TaskControlPopupEvent()

    data class EditTask(
        val id: Long
    ): TaskControlPopupEvent()

    data class ShowPopup(
        val id: Long,
        val name: String
    ): TaskControlPopupEvent()

    object HidePopup : TaskControlPopupEvent()
}