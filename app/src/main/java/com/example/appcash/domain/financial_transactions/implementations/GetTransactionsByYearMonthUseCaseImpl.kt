package com.example.appcash.domain.financial_transactions.implementations

import com.example.appcash.data.entities.FinancialTransaction
import com.example.appcash.data.repository_interfaces.FinancialTransactionsRepository
import com.example.appcash.data.vo.IconFolderVO
import com.example.appcash.domain.financial_transactions.interfaces.GetTransactionsByYearMonthUseCase
import kotlinx.coroutines.flow.Flow
import java.time.YearMonth
import javax.inject.Inject

class GetTransactionsByYearMonthUseCaseImpl @Inject constructor(
    private val repository: FinancialTransactionsRepository
): GetTransactionsByYearMonthUseCase {
    override fun invoke(yearMonth: YearMonth): Flow<Map<FinancialTransaction, IconFolderVO>> {
        val monthId = yearMonth.toString()
        return repository.getTransactionsByMonthId(monthId)
    }
}