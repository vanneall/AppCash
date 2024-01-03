package com.example.appcash.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appcash.data.Folder
import kotlinx.coroutines.flow.Flow

@Dao
interface FoldersDao {

    @Query("SELECT * FROM folder")
    fun getFolders(): Flow<List<Folder>>

    @Insert
    fun insertFolder(folder: Folder)

}