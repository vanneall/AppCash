package com.example.appcash.domain.financial_transactions.interfaces

import com.example.appcash.data.entities.Finance

interface InsertFinanceUseCase {
    operator fun invoke(value: Finance)
}