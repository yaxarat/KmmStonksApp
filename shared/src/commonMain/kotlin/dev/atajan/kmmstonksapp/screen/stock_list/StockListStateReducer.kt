package dev.atajan.kmmstonksapp.screen.stock_list

import dev.atajan.kmmstonksapp.cache.DatabaseOperations
import dev.atajan.kmmstonksapp.cache.Stock
import dev.atajan.kmmstonksapp.cache.Ticker
import dev.atajan.kmmstonksapp.network.StocksApi
import dev.atajan.kmmstonksapp.viewModel.StateManager
import dev.atajan.kmmstonksapp.viewModel.StateReducers

fun StateReducers.showLoadingIndicator() {
    stateManager.updateScreen(StockListState::class) {
        it.copy(
            isLoading = true
        )
    }
}

fun StateReducers.updateStocks() {
    refreshStocksOnScreen(
        databaseOperations = DatabaseOperations(dataRepository.appDatabase),
        stateManager = stateManager
    )
}

suspend fun StateReducers.insertTicker(tickerSymbol: String) {
    val operation = DatabaseOperations(dataRepository.appDatabase)
    operation.insertTickerSymbol(tickerSymbol)

    StocksApi().get(tickerSymbol).let {
        operation.createStocks(listOf(it))
    }


    refreshStocksOnScreen(
        databaseOperations = operation,
        stateManager = stateManager
    )
}

suspend fun StateReducers.insertTickers(tickerSymbolList: List<String>) {
    val api = StocksApi()
    val operation = DatabaseOperations(dataRepository.appDatabase)
    val stockList = mutableListOf<Stock>()

    for (tickerSymbol in tickerSymbolList) {
        operation.insertTickerSymbol(tickerSymbol)
        stockList.add(api.get(tickerSymbol).copy(tickerSymbol = Ticker(ticker = tickerSymbol)))
    }

    operation.createStocks(stockList)

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