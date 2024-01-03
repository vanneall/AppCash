package com.example.appcash.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appcash.data.Folder
import com.example.appcash.data.dao.FoldersDao

@Database(version = 1, entities = [Folder::class])
abstract class FoldersDatabase : RoomDatabase() {
    abstract fun getFoldersDao(): FoldersDao

}