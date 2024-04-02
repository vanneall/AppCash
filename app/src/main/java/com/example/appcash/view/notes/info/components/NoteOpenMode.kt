package com.example.appcash.view.notes.info.components

import com.example.appcash.utils.mode.OpenMode
import com.example.appcash.utils.mode.OpenModeHandler

enum class NoteOpenMode: OpenMode {
    CREATE,
    EDIT,
    UNKNOWN;

    object Definition: OpenModeHandler {

        override val ERROR_VALUE_STRING = UNKNOWN.name

        override fun handle(mode: String): NoteOpenMode {
            return when (mode) {
                CREATE.name -> CREATE
                EDIT.name -> EDIT
                else -> UNKNOWN
            }
        }
    }
}