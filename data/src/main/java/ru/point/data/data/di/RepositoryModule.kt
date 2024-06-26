package ru.point.data.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.point.data.data.datasource.remote.api.FinanceApi
import ru.point.data.data.datasource.remote.api.FolderApi
import ru.point.data.data.datasource.remote.api.NoteApi
import ru.point.data.data.datasource.remote.api.TaskApi
import ru.point.data.data.datasource.repository.implementations.remote.RemoteCategoriesRepository
import ru.point.data.data.datasource.repository.implementations.remote.RemoteFinanceRepository
import ru.point.data.data.datasource.repository.implementations.remote.RemoteNoteRepository
import ru.point.data.data.datasource.repository.implementations.remote.RemoteTasksRepository
import ru.point.data.data.datasource.repository.interfaces.CategoriesRepository
import ru.point.data.data.datasource.repository.interfaces.FinancesRepository
import ru.point.data.data.datasource.repository.interfaces.NotesRepository
import ru.point.data.data.datasource.repository.interfaces.TasksRepository
import ru.point.data.data.datasource.settings.SettingsStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSettingsStore(@ApplicationContext context: Context): SettingsStore {
        return SettingsStore(context)
    }

//    @Provides
//    @Singleton
//    fun provideFoldersRepository(database: AppCashDatabase): CategoriesRepository {
//        return CategoriesRepositoryImpl(categoryDao = database.getCategoryDao())
//    }
//
//    @Provides
//    @Singleton
//    fun provideNoteRepository(database: AppCashDatabase): NotesRepository {
//        return NotesRepositoryImpl(noteDao = database.getNoteDao())
//    }
//
//    @Provides
//    @Singleton
//    fun provideTasksRepository(database: AppCashDatabase): TasksRepository {
//        return TasksRepositoryImpl(taskDao = database.getTaskDao())
//    }
//
//    @Provides
//    @Singleton
//    fun provideFinancialTransactionsRepository(database: AppCashDatabase): FinancesRepository {
//        return FinancesRepositoryImpl(financeDao = database.getFinanceDao())
//    }

    @Provides
    @Singleton
    fun provideRemoteCategoriesRepository(api: FolderApi): CategoriesRepository {
        return RemoteCategoriesRepository(api = api)
    }

    @Provides
    @Singleton
    fun provideRemoteNoteRepository(api: NoteApi): NotesRepository {
        return RemoteNoteRepository(api = api)
    }

    @Provides
    @Singleton
    fun provideRemoteTaskRepository(api: TaskApi): TasksRepository {
        return RemoteTasksRepository(api = api)
    }

    @Provides
    @Singleton
    fun provideRemoteFinancialTransactionsRepository(api: FinanceApi): FinancesRepository {
        return RemoteFinanceRepository(api = api)
    }
}