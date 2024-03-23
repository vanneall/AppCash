package com.example.appcash.view.finance.add_folder.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.data.entities.Category.Discriminator
import com.example.appcash.domain.notes.interfaces.InsertFolderUseCase
import com.example.appcash.utils.ParamsStore.colorsList
import com.example.appcash.utils.ParamsStore.getRandomColorIndex
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
    private val insertFolderUseCase: InsertFolderUseCase
) : ViewModel(), EventHandler {
    private val _state = MutableStateFlow(AddFolderState())

    val state = _state.asStateFlow()

    override fun handle(event: Event) {
        when (event) {
            is AddFolderEvent.InputNameEvent -> {
                _state.update {
                    it.copy(
                        name = event.name
                    )
                }
            }

            is AddFolderEvent.CreateFolderEvent -> {
                viewModelScope.launch(Dispatchers.IO) {
                    insertFolderUseCase(
                        name = _state.value.name,
                        colorIndex = colorsList.getRandomColorIndex(),
                        discriminator = Discriminator.FINANCES,
                        iconId = event.iconId
                    )
                }
            }
        }
    }
}