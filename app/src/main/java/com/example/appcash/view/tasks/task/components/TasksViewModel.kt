package com.example.appcash.view.tasks.task.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.data.entities.MainTask
import com.example.appcash.data.entities.SubTask
import com.example.appcash.domain.notes.implementations.GetFolderNameByIdUseCaseImpl
import com.example.appcash.domain.tasks.implementations.GetMapTasksByFolderIdUseCaseImpl
import com.example.appcash.domain.tasks.implementations.GetMapTasksUseCaseImpl
import com.example.appcash.domain.tasks.implementations.InsertMainTaskUseCaseImpl
import com.example.appcash.domain.tasks.implementations.InsertSubTaskUseCaseImpl
import com.example.appcash.domain.tasks.implementations.InsertTaskToFolderLinkUseCaseImpl
import com.example.appcash.domain.tasks.implementations.UpdateTaskUseCaseImpl
import com.example.appcash.utils.ArgsKeys
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import com.example.appcash.utils.events.SearchEvent
import com.example.appcash.view.notes.notes_folder.components.FolderOpenMode
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TasksViewModel @AssistedInject constructor(
    @Assisted(ArgsKeys.FOLDER_ID_KEY)
    private val id: Long,
    @Assisted(ArgsKeys.OPEN_MODE_KEY)
    private val mode: FolderOpenMode,
    private val insertTaskToFolderLinkUseCaseImpl: InsertTaskToFolderLinkUseCaseImpl,
    private val getFolderNameByIdUseCase: GetFolderNameByIdUseCaseImpl,
    private val getMapTasksUseCase: GetMapTasksUseCaseImpl,
    private val getMapTasksByFolderIdUseCase: GetMapTasksByFolderIdUseCaseImpl,
    private val insertSubTaskUseCase: InsertSubTaskUseCaseImpl,
    private val insertMainTaskUseCase: InsertMainTaskUseCaseImpl,
    private val updateTaskUseCase: UpdateTaskUseCaseImpl
) : ViewModel(), EventHandler {

    private val _state = initializePrivateState()

    private val _searchQuery = MutableStateFlow("")

    private val _folderName = initializePrivateFolderName()

    val state = combine(_state, _searchQuery, _folderName) { state, query, name ->
        TasksState(
            folderName = name,
            values = state.filterKeys { it.text.contains(query) },
            querySearch = query
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TasksState())

    override fun handle(event: Event) {
        when (event) {
            is TaskEvent.CreateTaskEvent -> {
                viewModelScope.launch(Dispatchers.IO) {
                    when {
                        event.mainTaskId != null -> {
                            insertSubTaskUseCase(
                                mainId = event.mainTaskId, subTask = SubTask(text = event.title)
                            )
                        }
                        else -> {
                            insertMainTaskUseCase(
                                folderId = id,
                                mainTask = MainTask(text = event.title)
                            )
                        }
                    }
                }
            }
            is SearchEvent -> {
                _searchQuery.update { event.query }
            }
            is TaskEvent.UpdateCheckBoxEvent -> {
                viewModelScope.launch(Dispatchers.IO) {
                    updateTaskUseCase.invoke(event.task)
                }
            }
        }
    }

    private fun initializePrivateState(): Flow<Map<MainTask, List<SubTask>?>> {
        return when (mode) {
            FolderOpenMode.ALL -> {
                getMapTasksUseCase.invoke()
            }

            FolderOpenMode.DEFINED -> {
                getMapTasksByFolderIdUseCase.invoke(id = id)
            }
            else -> flowOf()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())
    }

    private fun initializePrivateFolderName(): Flow<String> {
        return when (mode) {
            FolderOpenMode.ALL -> {
                flowOf("Все задачи")
            }

            FolderOpenMode.DEFINED -> {
                getFolderNameByIdUseCase.invoke(id = id)
            }
            else -> flowOf()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")
    }
}