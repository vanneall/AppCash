package com.example.appcash.view.popup.taskconfigurator

import com.example.appcash.utils.events.Event
import java.time.LocalDate

sealed class TaskConfiguratorPopupEvent : Event {

    data class ShowPopup(
        val id: Long? = null,
        val parentId: Long? = null,
        val name: String? = null,
        val description: String? = null,
        val date: LocalDate = LocalDate.now()
    ) : TaskConfiguratorPopupEvent()

    object HidePopup : TaskConfiguratorPopupEvent()

    data class InsertName(val name: String) : TaskConfiguratorPopupEvent()

    data class InsertDescription(val description: String) : TaskConfiguratorPopupEvent()

    data class SelectDate(val localDate: LocalDate) : TaskConfiguratorPopupEvent()

    data class CreateTask(val parentTaskId: Long?) : TaskConfiguratorPopupEvent()

    object ShowDatePickerDialog : TaskConfiguratorPopupEvent()

    object HideDatePickerDialog : TaskConfiguratorPopupEvent()
}