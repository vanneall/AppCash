package com.example.appcash.view.finance.newfinance.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.R
import com.example.appcash.utils.FolderIconMapper
import com.example.appcash.utils.events.Event
import com.example.appcash.view.FabState
import com.example.appcash.view.TopAppBarState
import com.example.appcash.view.finance.newfinance.components.AddFinanceEvent
import com.example.appcash.view.finance.newfinance.components.AddFinanceState
import com.example.appcash.view.finance.newfinance.components.AddFinanceViewModel
import com.example.appcash.view.notes.notefolders.screen.CategoryListItem
import com.example.appcash.view.popup.create.CreateCategoryPopup
import com.example.appcash.view.popup.create.CreateCategoryPopupEvent
import com.example.appcash.view.ui.theme.Gray
import com.example.appcash.view.ui.theme.LightGray
import com.example.appcash.view.ui.theme.LightGray2

@Composable
fun AddFinanceScreen(
    viewModel: AddFinanceViewModel,
    navigateTo: (String) -> Unit,
    navigateBack: () -> Unit,
    topAppBarState: MutableState<TopAppBarState>,
    fabState: MutableState<FabState>
) {
    topAppBarState.value = TopAppBarState(
        title = stringResource(id = R.string.finance_screen),
        navigationIcon = {
            IconButton(
                onClick = {
                    navigateBack()
                },
                modifier = Modifier
                    .size(36.dp)
                    .background(color = LightGray, shape = CircleShape)
                    .padding(4.dp)
            ) {
                androidx.compose.material.Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    modifier = Modifier
                )
            }
        }
    )

    fabState.value = FabState { viewModel.handle(AddFinanceEvent.CreateTransactionEvent) }

    AddFinance(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::handle,
        navigateTo = navigateTo,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddFinance(
    state: AddFinanceState,
    onEvent: (Event) -> Unit,
    navigateTo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "68 000 Р",
            fontSize = 36.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.fillMaxWidth()
        )



        Spacer(modifier = Modifier.height(40.dp))

        NavigateAddButton(
            state = state,
            onEvent = onEvent,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))

        TextField(
            value = state.price,
            onValueChange = { onEvent(AddFinanceEvent.InputPriceEvent(it)) },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(fontSize = 14.sp),
            label = { Text(text = "Введите цену") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = LightGray,
                focusedContainerColor = LightGray
            )
        )

        Spacer(modifier = Modifier.height(40.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            item {
                CategoryListItem(
                    name = stringResource(id = R.string.add_new_category),
                    countOfInnerItems = "",
                    icon = painterResource(id = R.drawable.add_task_icon),
                    iconColor = Gray,
                    iconBackgroundColor = LightGray2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onEvent(CreateCategoryPopupEvent.ShowCreatePopup) }
                )
            }

            items(
                items = state.categories
            ) { folder ->
                CategoryListItem(
                    name = folder.name,
                    countOfInnerItems = "",
                    icon = FolderIconMapper.mapToIcon(value = folder.icon),
                    iconColor = Color.Black,
                    iconBackgroundColor = LightGray2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onEvent(AddFinanceEvent.SelectCategoryEvent(folder.id)) }
                )
            }
        }
    }
    if (state.createCategoryPopupState.isShowed) {
        ModalBottomSheet(
            onDismissRequest = { onEvent(CreateCategoryPopupEvent.HideCreatePopup) },
            containerColor = Color.White,
            modifier = Modifier
                .height(350.dp)
        ) {
            CreateCategoryPopup(
                state = state.createCategoryPopupState,
                onEvent = onEvent,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            )
        }
    }
}

@Composable
fun PriceInput(
    price: String,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = price,
        textStyle = TextStyle(textAlign = TextAlign.End),
        onValueChange = { newValue ->
            onEvent(
                AddFinanceEvent.InputPriceEvent(
                    price = newValue
                )
            )
        },
        singleLine = true,
        placeholder = {
            Text(
                text = "0",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        },
        shape = RoundedCornerShape(corner = CornerSize(14.dp)),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Decimal
        ),
        modifier = modifier
    )
}

@Composable
private fun NavigateAddButton(
    state: AddFinanceState,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedButton(
            onClick = { onEvent(AddFinanceEvent.SelectExpenseButton) },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (state.isIncomeButtonSelected) {
                    Color.White
                } else {
                    Color.Black
                },
            ),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.finance_expenses),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = if (!state.isIncomeButtonSelected)
                    Color.White
                else Color.Black,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }

        OutlinedButton(
            onClick = { onEvent(AddFinanceEvent.SelectIncomeButton) },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                if (state.isIncomeButtonSelected)
                    Color.Black
                else Color.Transparent
            ),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.finance_income),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = if (state.isIncomeButtonSelected)
                    Color.White
                else Color.Black,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun NavigateAddButtonPreview() {
    NavigateAddButton(
        AddFinanceState(),
        {}, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 10.dp)
    )
}