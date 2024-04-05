package com.example.appcash.view.tasks.list.components

import com.example.appcash.view.notes.notefolders.components.FolderOpenMode

enum class TasksSelections {
    ALL,
    ONLY_FOLDER,
    ONLY_BOOKMARKS,
    UNKNOWN;

    companion object {
        val ERROR_VALUE_STRING = FolderOpenMode.UNKNOWN.name

        fun handle(mode: String): TasksSelections {
            return when (mode) {
                ALL.name -> ALL
                ONLY_FOLDER.name -> ONLY_FOLDER
                ONLY_BOOKMARKS.name -> ONLY_BOOKMARKS
                else -> UNKNOWN
            }
        }
    }

}