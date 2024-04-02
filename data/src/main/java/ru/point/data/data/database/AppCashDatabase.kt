package ru.point.data.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.point.data.data.RoomConverters
import ru.point.data.data.dao.CategoryDao
import ru.point.data.data.dao.FinanceDao
import ru.point.data.data.dao.NoteDao
import ru.point.data.data.dao.TaskDao
import ru.point.data.data.entities.Category
import ru.point.data.data.entities.Finance
import ru.point.data.data.entities.Note
import ru.point.data.data.entities.Task

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