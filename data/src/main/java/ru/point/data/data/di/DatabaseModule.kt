package ru.point.data.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.point.data.data.database.AppCashDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideFoldersDatabase(@ApplicationContext context: Context): AppCashDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppCashDatabase::class.java,
            name = "Appcash.db"
        ).build()
    }
}