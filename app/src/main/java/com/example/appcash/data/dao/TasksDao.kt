package com.example.appcash.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.entities.SubTask
import com.example.appcash.data.entities.SubTaskToMainTaskLink
import com.example.appcash.data.entities.TaskToFolderLink
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Query("SELECT * FROM maintask")
    fun getTasks(): Flow<MainTask>

    @Query("SELECT * FROM maintask WHERE :id = id")
    fun getTasksById(id: Long): Flow<MainTask>

    @Query("SELECT COUNT(id) FROM maintask WHERE isCompleted = 1")
    fun getCompletedCount(): Flow<Int>

    @Query("SELECT COUNT(id) FROM maintask WHERE isCompleted = 0")
    fun getPlannedCount(): Flow<Int>


    @Query("SELECT mt.id, mt.text, mt.isCompleted, st.id, st.text, st.isCompleted " +
            "FROM maintask mt " +
            "LEFT JOIN SubTaskToMainTaskLink subToMain ON mt.id = subToMain.mainTaskId " +
            "LEFT JOIN subtask st ON subToMain.subTaskId = st.id "
    )
    fun getAllTasks(): Flow<Map<MainTask, List<SubTask>?>>

    @Query("SELECT mt.id, mt.text, mt.isCompleted, st.id, st.text, st.isCompleted " +
            "FROM maintask mt " +
            "JOIN tasktofolderlink tf ON tf.folderId = :id AND tf.taskId = mt.id " +
            "LEFT JOIN SubTaskToMainTaskLink subToMain ON mt.id = subToMain.mainTaskId " +
            "LEFT JOIN subtask st ON subToMain.subTaskId = st.id "
    )
    fun getAllTasksByFolderId(id: Long): Flow<Map<MainTask, List<SubTask>?>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMainTask(mainTask: MainTask): Long

    @Query("UPDATE maintask SET isCompleted = :isChecked WHERE id = :id")
    fun updateMainTask(id: Long, isChecked: Boolean)

    @Query("UPDATE subtask SET isCompleted = :isChecked WHERE id = :id")
    fun updateSubTask(id: Long, isChecked: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSubTask(subTask: SubTask): Long

    @Transaction
    fun insertMainTransaction(
        mainTask: MainTask,
        folderId: Long,
        insertTaskToFolderLink: (TaskToFolderLink) -> Unit
    ) {
        val taskId = insertMainTask(mainTask = mainTask)
        insertTaskToFolderLink(
            TaskToFolderLink(
                taskId = taskId,
                folderId = folderId,
            )
        )
    }

    @Transaction
    fun insertSubTaskTransaction(
        mainTaskId: Long,
        subTask: SubTask,
        insertSubToMainLink: (SubTaskToMainTaskLink) -> Unit) {
        val subId = insertSubTask(subTask)
        insertSubToMainLink(
            SubTaskToMainTaskLink(
                mainTaskId = mainTaskId,
                subTaskId = subId
            )
        )
    }
}