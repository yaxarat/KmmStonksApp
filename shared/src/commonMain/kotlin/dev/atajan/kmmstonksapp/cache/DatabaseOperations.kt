package dev.atajan.kmmstonksapp.cache

import dev.atajan.kmmstonksapp.shared.cache.AppDatabase

/**
 * Make sure it is only accessible from within the multiplatform module.
 */
internal class DatabaseOperations(database: AppDatabase) {
    private val dbQuery = database.appDatabaseQueries

    /**
     * Function to clear all the tables in the database in a single SQL transaction:
     */
    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllStocks()
            dbQuery.removeAllTickerSymbols()
        }
    }

    internal fun getAllStocks(): List<Stock> {
        return dbQuery.selectAllStocksInfo(::mapStockToModel).executeAsList()
    }

    private fun mapStockToModel(
        open: Double,
        high: Double,
        low: Double,
        current: Double,
        previous_close: Double,
        name: String,
        ticker: String,
        ticker_symbol: String?
    ): Stock {
        return Stock(
            open = open,
            high = high,
            low = low,
            current = current,
            previousClose = previous_close,
            name = name,
            tickerSymbol = Ticker(ticker = ticker)
        )
    }

    /**
     * Function to insert data into the database:
     */
    internal fun createStocks(stocks: List<Stock>) {
        dbQuery.transaction {
            stocks.forEach { stock ->
                val tickerSymbol = dbQuery.selectTickerBySymbol(stock.tickerSymbol.ticker).executeAsOneOrNull()
                
                if (tickerSymbol == null) {
                    insertTickerSymbol(tickerSymbol = stock.tickerSymbol.ticker)
                }

                insertStock(stock)
            }
        }
    }

    internal fun insertTickerSymbol(tickerSymbol: String) {
        dbQuery.insertTickerSymbol(ticker = tickerSymbol)
    }

    private fun insertStock(stock: Stock) {
        dbQuery.insertStock(
            open = stock.open,
            high = stock.high,
            low = stock.low,
            current = stock.current,
            previous_close = stock.previousClose,
            name = stock.name,
            ticker_symbol = stock.tickerSymbol.ticker
        )
    }

    internal fun getStockWithTicker(tickerSymbol: String): Stock {
        dbQuery.selectStockByTicker(tickerSymbol).executeAsOne().apply {
            return mapStockToModel(
                open = open,
                high = high,
                low = low,
                current = current,
                previous_close = previous_close,
                name = name,
                ticker = ticker_symbol,
                ticker_symbol = ticker_symbol
            )
        }
    }
}