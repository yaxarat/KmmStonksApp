package dev.atajan.kmmstonksapp.screen.stock_detail

import dev.atajan.kmmstonksapp.cache.DatabaseOperations
import dev.atajan.kmmstonksapp.screen.stock_list.StockListState
import dev.atajan.kmmstonksapp.viewModel.StateReducers

fun StateReducers.showLoadingIndicator() {
    stateManager.updateScreen(StockDetailState::class) {
        it.copy(
            isLoading = true
        )
    }
}

fun StateReducers.fetchStockDetail(ticker: String) {
    DatabaseOperations(dataRepository.appDatabase).getStockWithTicker(ticker).apply {
        stateManager.updateScreen(StockDetailState::class) {
            it.copy(
                isLoading = false,
                stock = this
            )
        }
    }
}