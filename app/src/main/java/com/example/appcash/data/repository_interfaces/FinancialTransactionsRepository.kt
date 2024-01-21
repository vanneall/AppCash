package com.example.appcash.data.repository_interfaces

import com.example.appcash.data.entities.FinancialTransaction
import com.example.appcash.data.entities.Folder
import kotlinx.coroutines.flow.Flow

interface FinancialTransactionsRepository {

    fun getTransactionsByMonthId(id: String): Flow<Map<FinancialTransaction, Folder>>

    fun getTransactionByFolders(id: String): Flow<Map<Folder, Int>>

}