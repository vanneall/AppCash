package com.example.appcash.view.finance.add_folder.components

import com.example.appcash.utils.events.Event

sealed class AddFolderEvent: Event {
    data class InputNameEvent(
        val name: String
    ): AddFolderEvent()

    data class CreateFolderEvent(
        val iconId: String
    ): AddFolderEvent()
}