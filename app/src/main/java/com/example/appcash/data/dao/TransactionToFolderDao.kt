package com.example.appcash.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.appcash.data.entities.TransactionToFolder

@Dao
interface TransactionToFolderDao {

    @Insert
    fun insert(value: TransactionToFolder)

}