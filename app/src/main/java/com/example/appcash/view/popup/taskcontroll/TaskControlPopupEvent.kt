package com.example.appcash.view.popup.taskcontroll

import com.example.appcash.utils.events.Event
import java.time.LocalDate

sealed class TaskControlPopupEvent : Event {

    data class DeleteTask(val id: Long): TaskControlPopupEvent()

    data class EditTask(
        val id: Long
    ): TaskControlPopupEvent()

    data class ShowPopup(
        val id: Long,
        val name: String,
        val description: String?,
        val localDate: LocalDate?
    ): TaskControlPopupEvent()

    object HidePopup : TaskControlPopupEvent()
}