package dev.atajan.kmmstonksapp.screen.stock_list

import dev.atajan.kmmstonksapp.cache.DatabaseOperations
import dev.atajan.kmmstonksapp.network.StocksApi
import dev.atajan.kmmstonksapp.viewModel.StateManager
import dev.atajan.kmmstonksapp.viewModel.StateReducers

fun StateReducers.updateStocks() {
    refreshStocksOnScreen(
        databaseOperations = DatabaseOperations(dataRepository.appDatabase),
        stateManager = stateManager
    )
}

suspend fun StateReducers.insertTicker(tickerSymbol: String) {
    val operation = DatabaseOperations(dataRepository.appDatabase)
    operation.insertTickerSymbol(tickerSymbol)

    val stockInfo = StocksApi().get(tickerSymbol).let {
        operation.createStocks(listOf(it))
    }


    refreshStocksOnScreen(
        databaseOperations = operation,
        stateManager = stateManager
    )
}

private fun refreshStocksOnScreen(
    databaseOperations: DatabaseOperations,
    stateManager: StateManager
) {
    var listData = databaseOperations.getAllStocks()

    // update CountriesListState, after retrieving data from the repository
    stateManager.updateScreen(StockListState::class) {
        it.copy(
            isLoading = false,
            stockListItems = listData,
        )
    }
}