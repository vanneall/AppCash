package com.example.appcash.domain.financial_transactions.interfaces

import com.example.appcash.data.entities.FinancialTransaction
import com.example.appcash.data.entities.Folder
import com.example.appcash.data.vo.IconFolderVO
import kotlinx.coroutines.flow.Flow
import java.time.YearMonth

interface GetTransactionsByYearMonthUseCase {

    fun invoke(yearMonth: YearMonth): Flow<Map<FinancialTransaction, IconFolderVO>>

}