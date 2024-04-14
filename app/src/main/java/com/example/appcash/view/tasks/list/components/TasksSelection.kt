package com.example.appcash.view.tasks.list.components

import co.yml.charts.common.extensions.isNotNull
import com.example.appcash.navigation.exception.AllTaskException
import com.example.appcash.navigation.exception.OnlyCategoryException

enum class TasksSelection {
    ALL,
    ONLY_CATEGORY,
    ONLY_BOOKMARKS,
    UNKNOWN;

    companion object {
        fun handle(mode: String?): TasksSelection {
            return when (mode) {
                ALL.name -> ALL
                ONLY_CATEGORY.name -> ONLY_CATEGORY
                ONLY_BOOKMARKS.name -> ONLY_BOOKMARKS
                else -> UNKNOWN
            }
        }

        fun checkIfScreenRulePassed(id: Long?, selections: TasksSelection) {
            if ((selections == ALL || selections == ONLY_BOOKMARKS) && id.isNotNull())
                throw AllTaskException(
                    message = "In all or bookmarks task screen category id is not null"
                )
            else if (selections == ONLY_CATEGORY && !id.isNotNull())
                throw OnlyCategoryException(
                    message = "In only category task screen category id is null"
                )
            else if (selections == UNKNOWN)
                throw Exception("Unknown selection")
        }

    }

}