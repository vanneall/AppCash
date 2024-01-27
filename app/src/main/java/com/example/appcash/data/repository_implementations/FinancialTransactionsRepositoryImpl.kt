package com.example.appcash.data.repository_implementations

import com.example.appcash.data.databases.FoldersDatabase
import com.example.appcash.data.entities.FinancialTransaction
import com.example.appcash.data.entities.Folder
import com.example.appcash.data.repository_interfaces.FinancialTransactionsRepository
import com.example.appcash.data.vo.IconFolderVO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FinancialTransactionsRepositoryImpl @Inject constructor(
    private val database: FoldersDatabase
) : FinancialTransactionsRepository {
    override fun getTransactionsByMonthId(id: String): Flow<Map<FinancialTransaction, IconFolderVO>> {
        return database.getFinancialTransactionDao().getTransactionByMonthId(id)
    }

    override fun getTransactionByFolders(id: String): Flow<Map<Folder, Int>> {
        return database.getFinancialTransactionDao().getTransactionByFolders(id)
    }

    override fun insertTransaction(value: FinancialTransaction, id: Long) {
        database.getFinancialTransactionDao().insertTransactionWithFolder(
            value,
            id,
            database.getTransactionToFolderDao()::insert
        )
    }
}