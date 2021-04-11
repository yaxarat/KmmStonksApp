package dev.atajan.kmmstonksapp.screen.stock_detail

import dev.atajan.kmmstonksapp.cache.Stock
import dev.atajan.kmmstonksapp.cache.Ticker
import dev.atajan.kmmstonksapp.viewModel.StateProvider

fun StateProvider.getStockDetailState(tickerSymbol: String): StockDetailState {

    /**
     *      the state gets initialized with "initState":
     *      WHEN this function is called for the first time
     *      OR if the "reinitWhen" condition is true after initialization, the "callOnInit" code gets called
     */
    return stateManager.getScreen(
        initState = {
            StockDetailState(
                isLoading = false,
                stock = Stock(
                    open = 0.0,
                    high = 0.0,
                    low = 0.0,
                    current = 0.0,
                    previousClose = 0.0,
                    name = "",
                    tickerSymbol = Ticker(ticker = tickerSymbol)
                )
            )
        },
        callOnInit = { events.loadStock(ticker = tickerSymbol) },
    )

}