package com.example.appcash.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.MapColumn
import androidx.room.Query
import androidx.room.Transaction
import com.example.appcash.data.entities.FinancialTransaction
import com.example.appcash.data.entities.Folder
import com.example.appcash.data.entities.TransactionToFolder
import com.example.appcash.data.vo.IconFolderVO
import kotlinx.coroutines.flow.Flow

@Dao
interface FinancialTransactionDao {

    @Query(
        "SELECT ft.id, ft.price, ft.date, f.id, f.name, f.color, f.type,  fi.iconId " +
                "FROM financialtransaction ft " +
                "JOIN transactiontofolder tf ON tf.transactionId = ft.id " +
                "JOIN folder f ON f.id = tf.folderId " +
                "JOIN foldertoicon fi " +
                "ON fi.folderId = f.id " +
                "WHERE ft.date = :monthId "
    )
    fun getTransactionByMonthId(monthId: String): Flow<Map<FinancialTransaction, IconFolderVO>>

    @Query("SELECT f.id, f.name, f.color, f.type,  fi.iconId " +
            "FROM folder f " +
            "JOIN foldertoicon fi " +
            "ON fi.folderId = f.id " +
            "WHERE f.type = \"FINANCIAL\" ")
    fun getIconFolder(): Flow<List<IconFolderVO>>

    @Query(
        "SELECT f.id, f.name, f.color, f.type, SUM(ft.price) as sum " +
                "FROM financialtransaction ft " +
                "JOIN transactiontofolder tf " +
                "ON tf.transactionId = ft.id " +
                "JOIN folder f " +
                "ON f.id = tf.folderId " +
                "GROUP BY f.id, f.name, f.color " +
                "HAVING ft.date = :monthId "
    )
    fun getTransactionByFolders(monthId: String):
            Flow<Map<Folder, @MapColumn(columnName = "sum") Int>>


    @Insert
    fun insertTransaction(finance: FinancialTransaction): Long

    @Transaction
    fun insertTransactionWithFolder(
        value: FinancialTransaction,
        id: Long,
        onInsertTransactionToFolder: (TransactionToFolder) -> Unit
    ) {
        val index = insertTransaction(value)
        onInsertTransactionToFolder(TransactionToFolder(folderId = id, transactionId = index))
    }
}