package dev.atajan.kmmstonksapp.screen.stock_list

import dev.atajan.kmmstonksapp.viewModel.Events

/********** INTERNAL event function, used by the StateProvider **********/

internal fun Events.loadStocks() = onMainCoroutine {
    stateReducers.updateStocks()
}

/********** PUBLIC event functions **********/

fun Events.insertTicker(tickerSymbol: String) = onMainCoroutine {
    stateReducers.insertTicker(tickerSymbol)
}

fun Events.insertTickers(tickerSymbols: List<String>) = onMainCoroutine {
    stateReducers.insertTickers(tickerSymbols)
}

fun Events.showLoadingIndicator() = onMainCoroutine {
    stateReducers.showLoadingIndicator()
}