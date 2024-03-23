package com.example.appcash.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.MapColumn
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.appcash.data.entities.Category
import com.example.appcash.data.entities.Finance
import kotlinx.coroutines.flow.Flow

@Dao
interface FinanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(finance: Finance): Long

    @Transaction
    @Query(
        "SELECT fin.id, fin.price, fin.date, fin.category_id, fol.id, fol.name, fol.color, fol.type, fol.icon " +
                "FROM finance fin " +
                "JOIN category fol ON fol.id = fin.category_id " +
                "WHERE fin.date = :monthId "
    )
    fun readByMonthId(monthId: String): Flow<Map<Finance, Category>>

    @Transaction
    @Query(
        "SELECT f.id, f.name, f.color, f.type, f.icon, SUM(ft.price) as sum " +
                "FROM finance ft " +
                "JOIN category f " +
                "ON f.id = ft.category_id " +
                "GROUP BY f.id, f.name, f.color " +
                "HAVING ft.date = :monthId "
    )
    fun readByFolder(monthId: String):
            Flow<Map<Category, @MapColumn(columnName = "sum") Int>>
}