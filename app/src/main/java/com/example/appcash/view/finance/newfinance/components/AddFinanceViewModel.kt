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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ru.point.data.data.entity.entities.Category
import ru.point.data.data.entity.entities.Finance
import ru.point.domain.category.interfaces.CreateCategoryUseCase
import ru.point.domain.category.interfaces.GetCategoryByTypeUseCase
import ru.point.domain.finance.interfaces.InsertFinanceUseCase
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddFinanceViewModel @Inject constructor(
    getCategoryByTypeUseCase: GetCategoryByTypeUseCase,
    private val insertFinanceUseCase: Lazy<InsertFinanceUseCase>,
    private val createCategoryUseCase: Lazy<CreateCategoryUseCase>
) : ViewModel(), EventHandler {

    private val _list = getCategoryByTypeUseCase.invoke(type = Category.Discriminator.Finance)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _price = MutableStateFlow("")

    private val _search = MutableStateFlow("")

    private val _isIncomeButtonSelected = MutableStateFlow(false)

    private val _categoryCreatePopupState = MutableStateFlow(CreateCategoryPopupState())

    private val _selectedId = MutableStateFlow<Long>(0)

    val state = combine(
        _list,
        _price,
        _search,
        _isIncomeButtonSelected,
        _categoryCreatePopupState,
    ) { list, price, search, isIncome, popupState ->
        AddFinanceState(
            price = price,
            search = search,
            categories = list.filter { it.name.contains(search) },
            isIncomeButtonSelected = isIncome,
            createCategoryPopupState = popupState
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), AddFinanceState())

    override fun handle(event: Event) {
        when (event) {

            is CreateCategoryPopupEvent.CreateCategory -> {
                createCategory()
            }

            is CreateCategoryPopupEvent.SelectIcon -> {
                selectFolderIcon(event.position)
            }

            is CreateCategoryPopupEvent.SelectColor -> {
                updateFolderColor(event.color, event.index)
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

            is AddFinanceEvent.SortCategoryEvent -> {
                updateSearch(event.text)
            }

            is AddFinanceEvent.SelectExpenseButton -> {
                selectExpense()
            }

            is AddFinanceEvent.SelectIncomeButton -> {
                selectIncome()
            }
        }
    }

    private fun createCategory() {
        with(_categoryCreatePopupState.value) {
            if (name.isEmpty()) {
                _categoryCreatePopupState.update { state -> state.copy(isNameTextFieldError = true) }
            } else {
                createCategoryUseCase.get().invoke(
                    name = name,
                    color = selectedColor,
                    discriminator = Category.Discriminator.Finance,
                    iconId = selectedFolderIcon,
                )
                hideBottomSheet()
            }
        }
    }

    private fun updateFolderColor(color: Int, index: Int) {
        _categoryCreatePopupState.update { state ->
            state.copy(
                selectedColor = color,
                selectedColorIndex = index
            )
        }
    }

    private fun updateSearch(text: String) {
        _search.update { text }
    }

    private fun selectFolderIcon(position: Int) {
        _categoryCreatePopupState.update { state ->
            state.copy(
                selectedFolderIcon = FolderIconMapper.mapToFolderIcon(position = position)
            )
        }
    }

    private fun showBottomSheet() {
        _categoryCreatePopupState.update { state ->
            state.copy(isShowed = true)
        }
    }

    private fun inputFolderName(name: String) {
        _categoryCreatePopupState.update { state ->
            state.copy(name = name)
        }
    }

    private fun hideBottomSheet() {
        _categoryCreatePopupState.update { CreateCategoryPopupState() }
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
        if (id == null || _price.value.isEmpty()) return

        var price = _price.value.toIntOrNull() ?: return

        price *= if (!_isIncomeButtonSelected.value) -1 else 1

        insertFinanceUseCase.get().invoke(
            value = Finance(
                price = price, folderId = id, date = LocalDate.now()
            )
        )
    }
}