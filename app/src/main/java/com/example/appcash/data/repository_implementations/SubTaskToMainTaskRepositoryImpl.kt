package com.example.appcash.data.repository_implementations

import com.example.appcash.data.databases.FoldersDatabase
import com.example.appcash.data.repository_interfaces.SubTaskToMainTaskRepository
import javax.inject.Inject

class SubTaskToMainTaskRepositoryImpl @Inject constructor(
    private val database: FoldersDatabase
): SubTaskToMainTaskRepository {

}