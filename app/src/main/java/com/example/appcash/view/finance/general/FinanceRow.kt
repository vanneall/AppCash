package com.example.appcash.view.finance.general

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcash.view.ui.theme.Dark
import com.example.appcash.view.ui.theme.LightGray2
import ru.point.data.data.entity.subset.FinanceSubset

@Composable
fun FinanceRow(
    icon: Painter,
    financeSubset: FinanceSubset,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            tint = Dark,
            contentDescription = null,
            modifier = Modifier
                .background(color = LightGray2, shape = CircleShape)
                .size(40.dp)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = financeSubset.folderName ?: "",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            fontSize = 18.sp,
            text = financeSubset.price.toString() + " ₽",
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black
        )
    }
}

@Preview
@Composable
private fun FinanceRowPreview() {
//    FinanceRow(
//        painterResource(id = R.drawable.car_folder_icon), Pair(
//            Finance(
//                1,
//                1600,
//                YearMonth.now(),
//                folderId = 1
//            ),
//            Category(
//                1,
//                "Категория 1",
//                1,
//                discriminator = Category.Discriminator.FINANCES,
//                icon = "wewew",
//            )
//        )
//    )
}