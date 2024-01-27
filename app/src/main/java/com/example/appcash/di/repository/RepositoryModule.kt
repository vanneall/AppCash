package com.example.appcash.di.repository

import com.example.appcash.data.databases.FoldersDatabase
import com.example.appcash.data.repository_implementations.FinancialTransactionsRepositoryImpl
import com.example.appcash.data.repository_implementations.FoldersRepositoryImpl
import com.example.appcash.data.repository_implementations.NoteRepositoryImpl
import com.example.appcash.data.repository_implementations.NoteToFolderLinkRepositoryImpl
import com.example.appcash.data.repository_implementations.TaskToFolderRepositoryImpl
import com.example.appcash.data.repository_implementations.TasksRepositoryImpl
import com.example.appcash.data.repository_interfaces.FinancialTransactionsRepository
import com.example.appcash.data.repository_interfaces.FoldersRepository
import com.example.appcash.data.repository_interfaces.NoteRepository
import com.example.appcash.data.repository_interfaces.NoteToFolderLinkRepository
import com.example.appcash.data.repository_interfaces.TaskToFolderRepository
import com.example.appcash.data.repository_interfaces.TasksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideFoldersRepository(database: FoldersDatabase): FoldersRepository {
        return FoldersRepositoryImpl(database = database)
    }

    @Provides
    @ViewModelScoped
    fun provideNoteToFolderLinkRepository(database: FoldersDatabase): NoteToFolderLinkRepository {
        return NoteToFolderLinkRepositoryImpl(database = database)
    }

    @Provides
    @ViewModelScoped
    fun provideNoteRepository(database: FoldersDatabase): NoteRepository {
        return NoteRepositoryImpl(database = database)
    }

    @Provides
    @ViewModelScoped
    fun provideTasksRepository(database: FoldersDatabase): TasksRepository {
        return TasksRepositoryImpl(database = database)
    }

    @Provides
    @ViewModelScoped
    fun provideTaskToFolderRepository(database: FoldersDatabase): TaskToFolderRepository {
        return TaskToFolderRepositoryImpl(database = database)
    }

    @Provides
    @ViewModelScoped
    fun provideFinancialTransactionsRepository(database: FoldersDatabase): FinancialTransactionsRepository {
        return FinancialTransactionsRepositoryImpl(database = database)
    }
}