package com.example.appcash.view.finance.newfinance.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcash.utils.FolderIconMapper
import com.example.appcash.utils.events.Event
import com.example.appcash.utils.events.EventHandler
import com.example.appcash.view.popup.create.CreateCategoryPopupEvent
import com.example.appcash.view.popup.create.CreateCategoryPopupState
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.point.data.data.entities.Category
import ru.point.data.data.entities.Finance
import ru.point.domain.finance.interfaces.InsertFinanceUseCase
import ru.point.domain.notes.interfaces.GetCategoryByTypeUseCase
import ru.point.domain.notes.interfaces.InsertFolderUseCase
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddFinanceViewModel @Inject constructor(
    getCategoryByTypeUseCase: GetCategoryByTypeUseCase,
    private val insertFinanceUseCase: Lazy<InsertFinanceUseCase>,
    private val insertFolderUseCase: Lazy<InsertFolderUseCase>
) : ViewModel(), EventHandler {

    private val _list = getCategoryByTypeUseCase
        .invoke(type = Category.Discriminator.FINANCES)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _price = MutableStateFlow("")

    private val _isIncomeButtonSelected = MutableStateFlow(false)

    private val _createPopupState = MutableStateFlow(CreateCategoryPopupState())

    private val _selectedId = MutableStateFlow<Long>(0)

    val state = combine(
        _list,
        _price,
        _isIncomeButtonSelected,
        _createPopupState,
    ) { list, price, isIncome, popupState ->
        AddFinanceState(
            price = price,
            categories = list,
            isIncomeButtonSelected = isIncome,
            createCategoryPopupState = popupState
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), AddFinanceState())

    override fun handle(event: Event) {
        when (event) {

            is CreateCategoryPopupEvent.InsertFolder -> {
                insertFolder(
                    name = event.name,
                    color = event.color
                )
            }

            is CreateCategoryPopupEvent.SelectFolderIcon -> {
                selectFolderIcon(event.position)
            }

            is CreateCategoryPopupEvent.ShowCreatePopup -> {
                showBottomSheet()
            }

            is CreateCategoryPopupEvent.HideCreatePopup -> {
                hideBottomSheet()
            }

            is CreateCategoryPopupEvent.InputName -> {
                inputFolderName(event.name)
            }

            is AddFinanceEvent.InputPriceEvent -> {
                updatePrice(price = event.price)
            }

            is AddFinanceEvent.CreateTransactionEvent -> {
                createFinance(id = _selectedId.value)
            }

            is AddFinanceEvent.SelectCategoryEvent -> {
                _selectedId.update { event.id }
            }

            is AddFinanceEvent.SelectExpenseButton -> {
                selectExpense()
            }

            is AddFinanceEvent.SelectIncomeButton -> {
                selectIncome()
            }
        }
    }

    private fun insertFolder(name: String, color: Int) {
        viewModelScope.launch(context = Dispatchers.IO) {
            insertFolderUseCase.get().invoke(
                name = name,
                colorIndex = color,
                discriminator = Category.Discriminator.FINANCES,
                iconId = _createPopupState.value.selectedFolderIcon,
            )
            hideBottomSheet()
        }
    }


    private fun selectFolderIcon(position: Int) {
        _createPopupState.update { state ->
            state.copy(
                selectedFolderIcon = FolderIconMapper.mapToFolderIcon(position = position)
            )
        }
    }

    private fun showBottomSheet() {
        _createPopupState.update { state ->
            state.copy(
                isShowed = true
            )
        }
    }

    private fun inputFolderName(name: String) {
        _createPopupState.update { state ->
            state.copy(
                name = name
            )
        }
    }

    private fun hideBottomSheet() {
        _createPopupState.update { CreateCategoryPopupState() }
    }

    private fun selectIncome() {
        _isIncomeButtonSelected.update { true }
    }

    private fun selectExpense() {
        _isIncomeButtonSelected.update { false }
    }

    private fun updatePrice(price: String) {
        _price.update { price }
    }


    private fun createFinance(id: Long?) {
        CoroutineScope(Dispatchers.IO).launch {
            if (id == null) return@launch
            insertFinanceUseCase.get().invoke(
                value = Finance(
                    price = _price.value.toInt(),
                    folderId = id,
                    date = LocalDate.now()
                )
            )
        }
    }
}