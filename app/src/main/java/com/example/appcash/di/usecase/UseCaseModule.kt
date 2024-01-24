package com.example.appcash.di.usecase

import com.example.appcash.data.repository_interfaces.FinancialTransactionsRepository
import com.example.appcash.data.repository_interfaces.FoldersRepository
import com.example.appcash.data.repository_interfaces.NoteRepository
import com.example.appcash.data.repository_interfaces.NoteToFolderLinkRepository
import com.example.appcash.data.repository_interfaces.TaskToFolderRepository
import com.example.appcash.data.repository_interfaces.TasksRepository
import com.example.appcash.domain.financial_transactions.implementations.GetTransactionsByFolderUseCaseImpl
import com.example.appcash.domain.financial_transactions.implementations.GetTransactionsByYearMonthUseCaseImpl
import com.example.appcash.domain.financial_transactions.implementations.InsertFinanceUseCaseImpl
import com.example.appcash.domain.financial_transactions.implementations.InsertFolderWithIconUseCaseImpl
import com.example.appcash.domain.financial_transactions.interfaces.GetTransactionsByFolderUseCase
import com.example.appcash.domain.financial_transactions.interfaces.GetTransactionsByYearMonthUseCase
import com.example.appcash.domain.financial_transactions.interfaces.InsertFinanceUseCase
import com.example.appcash.domain.financial_transactions.interfaces.InsertFolderWithIconUseCase
import com.example.appcash.domain.notes.implementations.GetFolderNameByIdUseCaseImpl
import com.example.appcash.domain.notes.implementations.GetFolderByTypeUseCaseImpl
import com.example.appcash.domain.notes.implementations.GetNoteByIdUseCaseImpl
import com.example.appcash.domain.notes.implementations.GetNotesByFolderIdUseCaseImpl
import com.example.appcash.domain.notes.implementations.GetNotesUseCaseImpl
import com.example.appcash.domain.notes.implementations.InsertFolderUseCaseImpl
import com.example.appcash.domain.notes.implementations.UpsertNoteUseCaseImpl
import com.example.appcash.domain.notes.interfaces.GetFolderNameByIdUseCase
import com.example.appcash.domain.notes.interfaces.GetFoldersByTypeUseCase
import com.example.appcash.domain.notes.interfaces.GetNoteByIdUseCase
import com.example.appcash.domain.notes.interfaces.GetNotesByFolderIdUseCase
import com.example.appcash.domain.notes.interfaces.GetNotesUseCase
import com.example.appcash.domain.notes.interfaces.InsertFolderUseCase
import com.example.appcash.domain.notes.interfaces.UpsertNoteUseCase
import com.example.appcash.domain.tasks.implementations.GetCompletedCountUseCaseImpl
import com.example.appcash.domain.tasks.implementations.GetMapTasksByFolderIdUseCaseImpl
import com.example.appcash.domain.tasks.implementations.GetMapTasksUseCaseImpl
import com.example.appcash.domain.tasks.implementations.GetPlannedCountUseCaseImpl
import com.example.appcash.domain.tasks.implementations.InsertMainTaskUseCaseImpl
import com.example.appcash.domain.tasks.implementations.InsertSubTaskUseCaseImpl
import com.example.appcash.domain.tasks.implementations.InsertTaskToFolderLinkUseCaseImpl
import com.example.appcash.domain.tasks.implementations.UpdateTaskUseCaseImpl
import com.example.appcash.domain.tasks.interfaces.GetCompletedCountUseCase
import com.example.appcash.domain.tasks.interfaces.GetMapTasksByFolderIdUseCase
import com.example.appcash.domain.tasks.interfaces.GetMapTasksUseCase
import com.example.appcash.domain.tasks.interfaces.GetPlannedCountUseCase
import com.example.appcash.domain.tasks.interfaces.InsertMainTaskUseCase
import com.example.appcash.domain.tasks.interfaces.InsertSubTaskUseCase
import com.example.appcash.domain.tasks.interfaces.InsertTaskToFolderLinkUseCase
import com.example.appcash.domain.tasks.interfaces.UpdateTaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideGetFoldersUseCase(repository: FoldersRepository): GetFoldersByTypeUseCase {
        return GetFolderByTypeUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertFolderUseCase(repository: FoldersRepository): InsertFolderUseCase {
        return InsertFolderUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetFolderNameByIdUseCase(repository: FoldersRepository): GetFolderNameByIdUseCase {
        return GetFolderNameByIdUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetNotesByFolderIdUseCase(repository: NoteToFolderLinkRepository): GetNotesByFolderIdUseCase {
        return GetNotesByFolderIdUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetNoteByIdUseCase(repository: NoteRepository): GetNoteByIdUseCase {
        return GetNoteByIdUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertNoteUseCase(repository: NoteRepository): UpsertNoteUseCase {
        return UpsertNoteUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetNotesUseCase(repository: NoteRepository): GetNotesUseCase {
        return GetNotesUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetCompletedCountUseCase(repository: TasksRepository): GetCompletedCountUseCase {
        return GetCompletedCountUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetPlannedCountUseCase(repository: TasksRepository): GetPlannedCountUseCase {
        return GetPlannedCountUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetMapTasksUseCase(repository: TasksRepository): GetMapTasksUseCase {
        return GetMapTasksUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertMainTasksUseCase(repository: TasksRepository): InsertMainTaskUseCase {
        return InsertMainTaskUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideUpdateTasksUseCase(repository: TasksRepository): UpdateTaskUseCase {
        return UpdateTaskUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetMapTasksByFolderIdUseCase(repository: TasksRepository): GetMapTasksByFolderIdUseCase {
        return GetMapTasksByFolderIdUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertSubTaskUseCase(repository: TasksRepository): InsertSubTaskUseCase {
        return InsertSubTaskUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertTaskToFolderLinkUseCase(repository: TaskToFolderRepository): InsertTaskToFolderLinkUseCase {
        return InsertTaskToFolderLinkUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetTransactionsByYearMonthUseCase(repository: FinancialTransactionsRepository): GetTransactionsByYearMonthUseCase {
        return GetTransactionsByYearMonthUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetTransactionsByFolderUseCase(repository: FinancialTransactionsRepository): GetTransactionsByFolderUseCase {
        return GetTransactionsByFolderUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertFinanceUseCase(repository: FinancialTransactionsRepository): InsertFinanceUseCase {
        return InsertFinanceUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertFolderWithIconUseCase(repository: FoldersRepository): InsertFolderWithIconUseCase {
        return InsertFolderWithIconUseCaseImpl(repository = repository)
    }
}