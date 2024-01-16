package com.example.appcash.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.appcash.data.entities.Folder
import com.example.appcash.data.dao.FoldersDao
import com.example.appcash.data.dao.NoteDao
import com.example.appcash.data.dao.NoteToFolderLinkDao
import com.example.appcash.data.dao.SubTaskToMainTaskDao
import com.example.appcash.data.dao.TaskToFolderDao
import com.example.appcash.data.dao.TasksDao
import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.entities.Note
import com.example.appcash.data.entities.NoteToFolderLink
import com.example.appcash.data.entities.SubTaskToMainTaskLink
import com.example.appcash.data.entities.SubTask
import com.example.appcash.data.entities.TaskToFolderLink
import com.example.appcash.utils.RoomConverters

@Database(
    version = 1,
    entities = [
        Folder::class,
        Note::class,
        NoteToFolderLink::class,
        SubTask::class,
        MainTask::class,
        SubTaskToMainTaskLink::class,
        TaskToFolderLink::class
    ]
)
@TypeConverters(RoomConverters::class)
abstract class FoldersDatabase : RoomDatabase() {
    abstract fun getFoldersDao(): FoldersDao

    abstract fun getNoteToFolderLinkDao(): NoteToFolderLinkDao

    abstract fun getNoteDao(): NoteDao

    abstract fun getTasksDao(): TasksDao

    abstract fun getSubTaskToMainTaskDao(): SubTaskToMainTaskDao

    abstract fun getTaskToFolderDao(): TaskToFolderDao

}