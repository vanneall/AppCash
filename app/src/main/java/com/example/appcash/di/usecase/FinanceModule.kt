package com.example.appcash.di.usecase

import com.example.appcash.data.repository_interfaces.FinancesRepository
import com.example.appcash.domain.financial_transactions.implementations.GetTransactionsByFolderUseCaseImpl
import com.example.appcash.domain.financial_transactions.implementations.GetTransactionsByYearMonthUseCaseImpl
import com.example.appcash.domain.financial_transactions.implementations.InsertFinanceUseCaseImpl
import com.example.appcash.domain.financial_transactions.interfaces.GetTransactionsByFolderUseCase
import com.example.appcash.domain.financial_transactions.interfaces.GetTransactionsByYearMonthUseCase
import com.example.appcash.domain.financial_transactions.interfaces.InsertFinanceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class FinanceModule {
    @Provides
    @ViewModelScoped
    fun provideGetTransactionsByYearMonthUseCase(repository: FinancesRepository): GetTransactionsByYearMonthUseCase {
        return GetTransactionsByYearMonthUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetTransactionsByFolderUseCase(repository: FinancesRepository): GetTransactionsByFolderUseCase {
        return GetTransactionsByFolderUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertFinanceUseCase(repository: FinancesRepository): InsertFinanceUseCase {
        return InsertFinanceUseCaseImpl(repository = repository)
    }
}