package com.example.appcash.view.popup

import com.example.appcash.utils.events.Event

sealed class ConfigPopupEvent : Event {

    data class DeleteTask(val id: Long): ConfigPopupEvent()

    data class BookmarkTask(
        val id: Long
    ): ConfigPopupEvent()

    data class EditTask(
        val id: Long
    ): ConfigPopupEvent()

    data class ShowPopup(
        val id: Long,
        val name: String
    ): ConfigPopupEvent()

    object HidePopup : ConfigPopupEvent()
}