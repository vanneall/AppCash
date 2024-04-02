package com.example.appcash.view.notes.notefolders.components

import com.example.appcash.utils.mode.OpenMode
import com.example.appcash.utils.mode.OpenModeHandler

enum class FolderOpenMode: OpenMode {
    ALL,
    DEFINED,
    UNKNOWN;

    object Definition: OpenModeHandler {

        override val ERROR_VALUE_STRING = UNKNOWN.name

        override fun handle(mode: String): FolderOpenMode {
            return when (mode) {
                ALL.name -> ALL
                DEFINED.name -> DEFINED
                else -> UNKNOWN
            }
        }
    }
}