package com.example.appcash.view.notes.notes_folders

import androidx.lifecycle.ViewModel
import com.example.appcash.data.Folder
import com.example.appcash.domain.notes.interfaces.GetFoldersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FoldersListViewModel @Inject constructor(
    private val getFoldersUseCase: GetFoldersUseCase
) : ViewModel() {

    val folderListFlow = getFoldersUseCase.invoke()
    val state = MutableStateFlow(Int)

    init {
        state.update {
            it
        }
    }
    //fun getFoldersList() = getFoldersUseCase.invoke()
}