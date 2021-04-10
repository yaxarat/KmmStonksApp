package dev.atajan.kmmstonksapp.screen.stock_list

import dev.atajan.kmmstonksapp.cache.Stock
import dev.atajan.kmmstonksapp.viewModel.ScreenState

data class StockListState (
    val isLoading: Boolean = false,
    val stockListItems : List<Stock> = emptyList()
): ScreenState