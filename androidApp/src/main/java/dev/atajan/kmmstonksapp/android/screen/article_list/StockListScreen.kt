package dev.atajan.kmmstonksapp.android.screen.article_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.atajan.kmmstonksapp.android.components.LoadingComponent
import dev.atajan.kmmstonksapp.screen.stock_list.StockListState
import dev.atajan.kmmstonksapp.screen.stock_list.insertTicker
import dev.atajan.kmmstonksapp.viewModel.Events

@Composable
fun StockListScreen(
    stockListState: StockListState,
    events: Events,
    onListItemClick: (String) -> Unit
) {
//    TextField(value = ) {
//
//    }
    if (stockListState.isLoading) {
        LoadingComponent()
    } else {
        if (stockListState.stockListItems.isEmpty()) {
            Text(
                text = "empty list",
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth()
                    .clickable {
                        events.insertTicker("AAPL")
                        events.insertTicker("MSFT")
                        events.insertTicker("COF")
                        events.insertTicker("TSLA")
                    },
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
//                stickyHeader {
//                    CountriesListHeader()
//                }
                items(
                    items = stockListState.stockListItems,
                    itemContent = { item ->
                        Text(text = item.name)
                    }
                )
            }
        }
    }
}