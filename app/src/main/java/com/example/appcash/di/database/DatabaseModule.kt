package com.example.appcash.di.database

import android.content.Context
import androidx.room.Room
import com.example.appcash.data.databases.FoldersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideFoldersDatabase(@ApplicationContext context: Context): FoldersDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = FoldersDatabase::class.java,
            name = "Folders.db"
        ).build()
    }
}