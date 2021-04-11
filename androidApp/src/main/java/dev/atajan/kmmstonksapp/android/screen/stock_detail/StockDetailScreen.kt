package dev.atajan.kmmstonksapp.android.screen.stock_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.atajan.kmmstonksapp.android.components.LoadingComponent
import dev.atajan.kmmstonksapp.screen.stock_detail.StockDetailState

@Composable
fun StockDetailScreen(stockDetailState: StockDetailState) {
    if (stockDetailState.isLoading) {
        LoadingComponent()
    } else {
        val stock = stockDetailState.stock
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            Text(text = stock.name, style = MaterialTheme.typography.h3, modifier = Modifier.padding(bottom = 40.dp))
            Text(text = "Current price: ${stock.current}", style = MaterialTheme.typography.body1, modifier = Modifier.padding(bottom = 15.dp))
            Text(text = "High price: ${stock.high}", style = MaterialTheme.typography.body1, modifier = Modifier.padding(bottom = 15.dp))
            Text(text = "Low price: ${stock.low}", style = MaterialTheme.typography.body1, modifier = Modifier.padding(bottom = 15.dp))
            Text(text = "Opening price: ${stock.open}", style = MaterialTheme.typography.body1, modifier = Modifier.padding(bottom = 15.dp))
            Text(text = "Previous close price: ${stock.previousClose}", style = MaterialTheme.typography.body1, modifier = Modifier.padding(bottom = 15.dp))
        }
    }
}