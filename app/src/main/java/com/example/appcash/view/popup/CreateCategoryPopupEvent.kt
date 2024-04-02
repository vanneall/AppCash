package com.example.appcash.view.popup

import com.example.appcash.utils.events.Event

sealed class CreateCategoryPopupEvent() : Event {

    data class InsertFolder(
        val name: String,
        val color: Int
    ) : CreateCategoryPopupEvent()

    data class InputName(
        val name: String
    ) : CreateCategoryPopupEvent()

     object ShowCreatePopup: CreateCategoryPopupEvent()

    object HideCreatePopup : CreateCategoryPopupEvent()
}