package com.example.appcash.di.usecase

import com.example.appcash.data.repository_interfaces.FinancesRepository
import com.example.appcash.domain.financial_transactions.implementations.GetFinancesByFolderUseCaseImpl
import com.example.appcash.domain.financial_transactions.implementations.GetFinancesByYearMonthUseCaseImpl
import com.example.appcash.domain.financial_transactions.implementations.InsertFinanceUseCaseImpl
import com.example.appcash.domain.financial_transactions.interfaces.GetFinancesByFolderUseCase
import com.example.appcash.domain.financial_transactions.interfaces.GetFinancesByYearMonthUseCase
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
    fun provideGetTransactionsByYearMonthUseCase(repository: FinancesRepository): GetFinancesByYearMonthUseCase {
        return GetFinancesByYearMonthUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetTransactionsByFolderUseCase(repository: FinancesRepository): GetFinancesByFolderUseCase {
        return GetFinancesByFolderUseCaseImpl(repository = repository)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertFinanceUseCase(repository: FinancesRepository): InsertFinanceUseCase {
        return InsertFinanceUseCaseImpl(repository = repository)
    }
}