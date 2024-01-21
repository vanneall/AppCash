package com.example.appcash.domain.financial_transactions.interfaces

import com.example.appcash.data.entities.FinancialTransaction
import com.example.appcash.data.entities.Folder
import com.example.appcash.data.vo.FinanceCategoryVO
import kotlinx.coroutines.flow.Flow
import java.time.YearMonth

interface GetTransactionsByFolderUseCase {
    fun invoke(yearMonth: YearMonth): Flow<Map<FinanceCategoryVO, Int>>
}