package com.example.appcash.view.notes.note_info_screen.components

import com.example.appcash.utils.mode.OpenMode
import com.example.appcash.utils.mode.OpenModeHandler

enum class NoteOpenOpenMode: OpenMode {
    CREATE,
    EDIT;

    object Definition: OpenModeHandler {

        val DEFAULT_VALUE_STRING = CREATE.name

        override fun handle(mode: String): NoteOpenOpenMode {
            return when (mode) {
                EDIT.name -> EDIT
                else -> CREATE
            }
        }
    }
}