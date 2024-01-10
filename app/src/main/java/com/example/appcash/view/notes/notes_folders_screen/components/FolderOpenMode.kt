package com.example.appcash.view.notes.notes_folders_screen.components

import com.example.appcash.utils.mode.OpenMode
import com.example.appcash.utils.mode.OpenModeHandler

enum class FolderOpenMode: OpenMode {
    ALL,
    SELECTED;

    object Definition: OpenModeHandler {

        val DEFAULT_VALUE_STRING = ALL.name

        override fun handle(mode: String): FolderOpenMode {
            return when (mode) {
                SELECTED.name -> SELECTED
                else -> ALL
            }
        }
    }
}