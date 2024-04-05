package com.example.appcash.view.popup.taskconfigurator

import com.example.appcash.utils.events.Event

sealed class TaskConfiguratorPopupEvent : Event {

    data class ShowPopup(
        val id: Long? = null,
        val parentId: Long? = null,
        val name: String? = null,
        val description: String? = null,
        val date: String? = null
    ) : TaskConfiguratorPopupEvent()
    object HidePopup : TaskConfiguratorPopupEvent()

    data class SelectFolder(val folderId: Long) : TaskConfiguratorPopupEvent()

    data class InsertName(val name: String) : TaskConfiguratorPopupEvent()

    data class InsertDescription(val description: String) : TaskConfiguratorPopupEvent()

    data class SelectDate(val stringDate: String) : TaskConfiguratorPopupEvent()

    data class CreateTask(val parentTaskId: Long?) : TaskConfiguratorPopupEvent()
}