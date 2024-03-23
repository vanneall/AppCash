package com.example.appcash.di.repository

import com.example.appcash.data.database.AppCashDatabase
import com.example.appcash.data.repository_implementations.CategoriesRepositoryImpl
import com.example.appcash.data.repository_implementations.FinancesRepositoryImpl
import com.example.appcash.data.repository_implementations.NotesRepositoryImpl
import com.example.appcash.data.repository_implementations.TasksRepositoryImpl
import com.example.appcash.data.repository_interfaces.CategoriesRepository
import com.example.appcash.data.repository_interfaces.FinancesRepository
import com.example.appcash.data.repository_interfaces.NotesRepository
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
    fun provideFoldersRepository(database: AppCashDatabase): CategoriesRepository {
        return CategoriesRepositoryImpl(categoryDao = database.getCategoryDao())
    }

    @Provides
    @Singleton
    fun provideNoteRepository(database: AppCashDatabase): NotesRepository {
        return NotesRepositoryImpl(noteDao = database.getNoteDao())
    }

    @Provides
    @Singleton
    fun provideTasksRepository(database: AppCashDatabase): TasksRepository {
        return TasksRepositoryImpl(taskDao = database.getTaskDao())
    }

    @Provides
    @Singleton
    fun provideFinancialTransactionsRepository(database: AppCashDatabase): FinancesRepository {
        return FinancesRepositoryImpl(financeDao = database.getFinanceDao())
    }
}