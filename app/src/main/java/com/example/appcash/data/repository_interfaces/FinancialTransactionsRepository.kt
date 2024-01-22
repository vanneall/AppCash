package com.example.appcash.data.repository_interfaces

import com.example.appcash.data.entities.FinancialTransaction
import com.example.appcash.data.entities.Folder
import com.example.appcash.data.vo.IconFolderVO
import kotlinx.coroutines.flow.Flow

interface FinancialTransactionsRepository {

    fun getTransactionsByMonthId(id: String): Flow<Map<FinancialTransaction, IconFolderVO>>

    fun getTransactionByFolders(id: String): Flow<Map<Folder, Int>>

    fun insertTransaction(value: FinancialTransaction, id: Long)

}