package com.example.appcash.data.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.example.appcash.data.entities.NoteToFolderLink
import com.example.appcash.data.entities.SubTaskToMainTaskLink

@Dao
interface SubTaskToMainTaskDao {

    @Upsert
    fun insertSubToMainLink(subTaskToMainTaskLink: SubTaskToMainTaskLink)

}