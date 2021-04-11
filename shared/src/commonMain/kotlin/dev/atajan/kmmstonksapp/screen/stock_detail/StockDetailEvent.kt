package dev.atajan.kmmstonksapp.screen.stock_detail

import dev.atajan.kmmstonksapp.screen.stock_list.updateStocks
import dev.atajan.kmmstonksapp.viewModel.Events

/********** INTERNAL event function, used by the StateProvider **********/

internal fun Events.loadStock(ticker: String) = onMainCoroutine {
    stateReducers.fetchStockDetail(ticker = ticker)
}