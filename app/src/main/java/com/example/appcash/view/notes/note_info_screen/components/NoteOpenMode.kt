package com.example.appcash.view.notes.note_info_screen.components

import com.example.appcash.utils.mode.OpenMode
import com.example.appcash.utils.mode.OpenModeHandler

enum class NoteOpenMode: OpenMode {
    CREATE,
    EDIT;

    object Definition: OpenModeHandler {

        override val ERROR_VALUE_STRING = CREATE.name

        override fun handle(mode: String): NoteOpenMode {
            return when (mode) {
                EDIT.name -> EDIT
                else -> CREATE
            }
        }
    }
}