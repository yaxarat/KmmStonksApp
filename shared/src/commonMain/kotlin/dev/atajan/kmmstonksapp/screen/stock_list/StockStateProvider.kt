package dev.atajan.kmmstonksapp.screen.stock_list

import dev.atajan.kmmstonksapp.viewModel.StateProvider

/**
 *      the state gets initialized with "initState":
 *      WHEN this function is called for the first time
 *      OR if the "reinitWhen" condition is true after initialization, the "callOnInit" code gets called
 */
fun StateProvider.getStockListState() : StockListState {
    return stateManager.getScreen(
        initState = { StockListState(isLoading = true) },
        callOnInit = { events.loadStocks() }
    )
}