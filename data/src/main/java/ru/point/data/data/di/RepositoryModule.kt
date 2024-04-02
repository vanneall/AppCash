package ru.point.data.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.point.data.data.database.AppCashDatabase
import ru.point.data.data.repository_implementations.CategoriesRepositoryImpl
import ru.point.data.data.repository_implementations.FinancesRepositoryImpl
import ru.point.data.data.repository_implementations.NotesRepositoryImpl
import ru.point.data.data.repository_implementations.TasksRepositoryImpl
import ru.point.data.data.repository_interfaces.CategoriesRepository
import ru.point.data.data.repository_interfaces.FinancesRepository
import ru.point.data.data.repository_interfaces.NotesRepository
import ru.point.data.data.repository_interfaces.TasksRepository
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