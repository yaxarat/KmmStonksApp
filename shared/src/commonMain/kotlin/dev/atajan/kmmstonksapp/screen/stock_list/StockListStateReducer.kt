package dev.atajan.kmmstonksapp.screen.stock_list

import dev.atajan.kmmstonksapp.cache.DatabaseOperations
import dev.atajan.kmmstonksapp.viewModel.StateReducers

fun StateReducers.updateArticles() {
    var listData = DatabaseOperations(dataRepository.appDatabase).getAllStocks()

    // update CountriesListState, after retrieving data from the repository
    stateManager.updateScreen(StockListState::class) {
        it.copy(
            isLoading = false,
            stockListItems = listData,
        )
    }
}