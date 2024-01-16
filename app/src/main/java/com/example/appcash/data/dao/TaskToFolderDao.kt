package com.example.appcash.data.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.example.appcash.data.entities.NoteToFolderLink
import com.example.appcash.data.entities.TaskToFolderLink

@Dao
interface TaskToFolderDao {

    @Upsert
    fun insertTaskToFolderLink(taskToFolderLink: TaskToFolderLink)

}