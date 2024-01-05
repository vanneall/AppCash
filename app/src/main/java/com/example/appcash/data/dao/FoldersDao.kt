package com.example.appcash.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appcash.data.entities.Folder
import kotlinx.coroutines.flow.Flow

@Dao
interface FoldersDao {

    @Query("SELECT * FROM folder")
    fun getFolders(): Flow<List<Folder>>

    @Insert
    fun insertFolder(folder: Folder)

    @Query("SELECT name FROM folder WHERE id = :id")
    fun getFolderNameById(id: Long): Flow<String>

}