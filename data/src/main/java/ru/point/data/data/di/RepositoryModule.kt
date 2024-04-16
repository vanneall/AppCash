package ru.point.data.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.point.data.data.datasource.local.database.AppCashDatabase
import ru.point.data.data.datasource.repository.implementations.local.CategoriesRepositoryImpl
import ru.point.data.data.datasource.repository.implementations.local.FinancesRepositoryImpl
import ru.point.data.data.datasource.repository.implementations.local.NotesRepositoryImpl
import ru.point.data.data.datasource.repository.implementations.local.TasksRepositoryImpl
import ru.point.data.data.datasource.repository.interfaces.CategoriesRepository
import ru.point.data.data.datasource.repository.interfaces.FinancesRepository
import ru.point.data.data.datasource.repository.interfaces.NotesRepository
import ru.point.data.data.datasource.repository.interfaces.TasksRepository
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