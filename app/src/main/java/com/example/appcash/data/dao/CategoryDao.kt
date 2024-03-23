package com.example.appcash.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.appcash.data.entities.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(value: Category): Long

    @Query("SELECT * FROM category WHERE id = :id")
    fun readById(id: Long): Flow<Category>

    @Query("SELECT * FROM category WHERE type = :discriminator ")
    fun readByDiscriminator(discriminator: Category.Discriminator): Flow<List<Category>>

    @Transaction
    @Query(
        "UPDATE category " +
                "SET name = :name, color = :colorIndex " +
                "WHERE id = :id"
    )
    fun updateFolder(id: Long, name: String, colorIndex: Int)

    @Query("DELETE FROM category WHERE id = :value")
    fun deleteById(value: Long)
}