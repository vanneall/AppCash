package com.example.appcash.view.notes.notes_folders_screen.components

import com.example.appcash.utils.mode.OpenMode
import com.example.appcash.utils.mode.OpenModeHandler

enum class FolderOpenMode: OpenMode {
    ALL,
    DEFINED;

    object Definition: OpenModeHandler {

        override val DEFAULT_VALUE_STRING = ALL.name

        override fun handle(mode: String): FolderOpenMode {
            return when (mode) {
                DEFINED.name -> DEFINED
                else -> ALL
            }
        }
    }
}