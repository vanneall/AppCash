package com.example.appcash.view.popup.create

import com.example.appcash.utils.events.Event

sealed class CreateCategoryPopupEvent() : Event {

    object CreateCategory: CreateCategoryPopupEvent()

    data class SelectIcon(val position: Int) : CreateCategoryPopupEvent()

    data class SelectColor(val color: Int, val index: Int) : CreateCategoryPopupEvent()

    data class InputName(val name: String) : CreateCategoryPopupEvent()

    object ShowCreatePopup : CreateCategoryPopupEvent()

    object HideCreatePopup : CreateCategoryPopupEvent()
}