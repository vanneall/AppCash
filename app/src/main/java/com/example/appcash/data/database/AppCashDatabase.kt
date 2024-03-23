package com.example.appcash.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.appcash.data.dao.CategoryDao
import com.example.appcash.data.dao.FinanceDao
import com.example.appcash.data.dao.NoteDao
import com.example.appcash.data.dao.TaskDao
import com.example.appcash.data.entities.Category
import com.example.appcash.data.entities.Finance
import com.example.appcash.data.entities.Note
import com.example.appcash.data.entities.Task
import com.example.appcash.utils.RoomConverters

@Database(
    version = 1,
    entities = [
        Category::class,
        Note::class,
        Task::class,
        Finance::class,
    ]
)
@TypeConverters(RoomConverters::class)
abstract class AppCashDatabase : RoomDatabase() {
    abstract fun getCategoryDao(): CategoryDao

    abstract fun getNoteDao(): NoteDao

    abstract fun getTaskDao(): TaskDao

    abstract fun getFinanceDao(): FinanceDao

}