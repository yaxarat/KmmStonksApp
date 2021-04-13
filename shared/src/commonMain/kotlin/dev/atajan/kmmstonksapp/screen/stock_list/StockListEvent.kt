package dev.atajan.kmmstonksapp.screen.stock_list

import dev.atajan.kmmstonksapp.viewModel.Events

/********** INTERNAL event function, used by the StateProvider **********/

internal fun Events.loadStocks() = screenCoroutine(StockListState::class) {
    stateReducers.updateStocks()
}

/********** PUBLIC event functions **********/

fun Events.insertTicker(tickerSymbol: String) = screenCoroutine(StockListState::class) {
    stateReducers.insertTicker(tickerSymbol)
}

fun Events.insertTickers(tickerSymbols: List<String>) = screenCoroutine(StockListState::class) {
    stateReducers.insertTickers(tickerSymbols)
}

fun Events.showLoadingIndicator() = screenCoroutine(StockListState::class) {
    stateReducers.showLoadingIndicator()
}