package ru.point.data.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.point.data.data.RoomConverters
import ru.point.data.data.datasource.local.dao.CategoryDao
import ru.point.data.data.datasource.local.dao.FinanceDao
import ru.point.data.data.datasource.local.dao.NoteDao
import ru.point.data.data.datasource.local.dao.TaskDao
import ru.point.data.data.entity.entities.Category
import ru.point.data.data.entity.entities.Finance
import ru.point.data.data.entity.entities.Note
import ru.point.data.data.entity.entities.Task

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