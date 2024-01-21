package com.example.appcash.domain.financial_transactions.interfaces

import com.example.appcash.data.entities.FinancialTransaction

interface InsertFinanceUseCase {

    operator fun invoke(value: FinancialTransaction, id: Long)

}