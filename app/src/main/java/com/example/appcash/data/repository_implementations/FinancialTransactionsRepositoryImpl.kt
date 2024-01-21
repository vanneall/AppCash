package com.example.appcash.data.repository_implementations

import com.example.appcash.data.databases.FoldersDatabase
import com.example.appcash.data.entities.FinancialTransaction
import com.example.appcash.data.entities.Folder
import com.example.appcash.data.repository_interfaces.FinancialTransactionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FinancialTransactionsRepositoryImpl @Inject constructor(
    private val database: FoldersDatabase
) : FinancialTransactionsRepository {
    override fun getTransactionsByMonthId(id: String): Flow<Map<FinancialTransaction, Folder>> {
        return database.getFinancialTransactionDao().getTransactionByMonthId(id)
    }

    override fun getTransactionByFolders(id: String): Flow<Map<Folder, Int>> {
        return database.getFinancialTransactionDao().getTransactionByFolders(id)
    }
}