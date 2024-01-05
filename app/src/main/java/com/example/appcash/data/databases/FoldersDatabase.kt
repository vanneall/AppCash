package com.example.appcash.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appcash.data.entities.Folder
import com.example.appcash.data.dao.FoldersDao
import com.example.appcash.data.dao.NoteToFolderLinkDao
import com.example.appcash.data.entities.Note
import com.example.appcash.data.entities.NoteToFolderLink

@Database(version = 1, entities = [Folder::class, Note::class, NoteToFolderLink::class])
abstract class FoldersDatabase : RoomDatabase() {
    abstract fun getFoldersDao(): FoldersDao

    abstract fun getNoteToFolderLinkDao(): NoteToFolderLinkDao

}