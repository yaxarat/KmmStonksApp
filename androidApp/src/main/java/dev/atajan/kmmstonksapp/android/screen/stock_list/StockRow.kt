package dev.atajan.kmmstonksapp.android.screen.stock_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.atajan.kmmstonksapp.cache.Stock

@Composable
fun StockRow(
    stock: Stock,
    onClick: (String) -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            )
            .fillMaxWidth()
            .clickable {
                onClick(stock.tickerSymbol.ticker)
            },
        elevation = 8.dp,
    ) {

        Column {
//            CoilImage(
//                data = ,
//                contentDescription = ,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(IMAGE_HEIGHT.dp),
//                contentScale = ContentScale.Crop,
//            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    )
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth(0.55f)
                    .wrapContentWidth(Alignment.Start)
                    .align(Alignment.Bottom)
                ) {
                    Text(
                        text = stock.tickerSymbol.ticker,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                    Text(
                        text = stock.name,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                }

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Bottom)
                ) {
                    Text(
                        text = "\$${stock.current}",
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .padding(vertical = 6.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Previous close \$${stock.previousClose}",
                        textAlign = TextAlign.End,
                        color = Color.LightGray,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .padding(vertical = 6.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}