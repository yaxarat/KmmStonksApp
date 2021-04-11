package dev.atajan.kmmstonksapp.screen.stock_detail

import dev.atajan.kmmstonksapp.cache.Stock
import dev.atajan.kmmstonksapp.cache.Ticker
import dev.atajan.kmmstonksapp.viewModel.ScreenState

data class StockDetailState (
    val isLoading: Boolean = false,
    val stock: Stock,
): ScreenState