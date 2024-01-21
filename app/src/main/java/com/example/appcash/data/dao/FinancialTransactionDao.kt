package com.example.appcash.data.dao

import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import com.example.appcash.data.entities.FinancialTransaction
import com.example.appcash.data.entities.Folder
import kotlinx.coroutines.flow.Flow

@Dao
interface FinancialTransactionDao {

    @Query(
        "SELECT ft.id, ft.price, ft.date, f.id, f.name, f.color, f.type " +
                "FROM financialtransaction ft " +
                "JOIN transactiontofolder tf ON tf.transactionId = ft.id " +
                "JOIN folder f ON f.id = tf.folderId " +
                "WHERE ft.date = :monthId "
    )
    fun getTransactionByMonthId(monthId: String): Flow<Map<FinancialTransaction, Folder>>


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

}