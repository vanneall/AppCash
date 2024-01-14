package com.example.appcash.di.repository

import com.example.appcash.data.databases.FoldersDatabase
import com.example.appcash.data.repository_implementations.FoldersRepositoryImpl
import com.example.appcash.data.repository_implementations.NoteRepositoryImpl
import com.example.appcash.data.repository_implementations.NoteToFolderLinkRepositoryImpl
import com.example.appcash.data.repository_implementations.TasksRepositoryImpl
import com.example.appcash.data.repository_interfaces.FoldersRepository
import com.example.appcash.data.repository_interfaces.NoteRepository
import com.example.appcash.data.repository_interfaces.NoteToFolderLinkRepository
import com.example.appcash.data.repository_interfaces.TasksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideFoldersRepository(database: FoldersDatabase): FoldersRepository {
        return FoldersRepositoryImpl(database = database)
    }

    @Provides
    @Singleton
    fun provideNoteToFolderLinkRepository(database: FoldersDatabase): NoteToFolderLinkRepository {
        return NoteToFolderLinkRepositoryImpl(database = database)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(database: FoldersDatabase): NoteRepository {
        return NoteRepositoryImpl(database = database)
    }

    @Provides
    @Singleton
    fun provideTasksRepository(database: FoldersDatabase): TasksRepository {
        return TasksRepositoryImpl(database = database)
    }
}