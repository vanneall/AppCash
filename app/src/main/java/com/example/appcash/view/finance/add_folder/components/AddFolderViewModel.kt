package com.example.appcash.view.finance.add_folder.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.data.entities.Folder
import com.example.appcash.data.entities.FolderType
import com.example.appcash.domain.financial_transactions.interfaces.InsertFolderWithIconUseCase
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFolderViewModel @Inject constructor(
    private val insertFolderWithIconUseCase: InsertFolderWithIconUseCase
): ViewModel(), EventHandler {
    private val _state = MutableStateFlow(AddFolderState())

    val state = _state.asStateFlow()

    override fun handle(event: Event) {
        when(event) {
            is AddFolderEvent.InputNameEvent -> {
                _state.update {
                    it.copy(
                        name = event.name
                    )
                }
            }
            is AddFolderEvent.CreateFolderEvent -> {
                viewModelScope.launch(Dispatchers.IO) {
                    insertFolderWithIconUseCase(
                        folder = Folder(
                            name = _state.value.name,
                            color = 1,
                            type = FolderType.FINANCIAL
                        ),
                        event.iconId
                    )
                }
            }
        }
    }
}