package com.example.appcash.di.repository

import com.example.appcash.data.databases.FoldersDatabase
import com.example.appcash.data.repository_implementations.FoldersRepositoryImpl
import com.example.appcash.data.repository_implementations.NoteToFolderLinkRepositoryImpl
import com.example.appcash.data.repository_interfaces.FoldersRepository
import com.example.appcash.data.repository_interfaces.NoteToFolderLinkRepository
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
}