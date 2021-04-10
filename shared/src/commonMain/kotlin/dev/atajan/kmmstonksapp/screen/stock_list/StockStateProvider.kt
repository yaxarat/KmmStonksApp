package dev.atajan.kmmstonksapp.screen.stock_list

import dev.atajan.kmmstonksapp.viewModel.StateProvider

fun StateProvider.getStockListState() : StockListState {
    return stateManager.getScreen(
        initState = { StockListState(isLoading = true) },
        callOnInit = { events.loadStocks() }
    )
}