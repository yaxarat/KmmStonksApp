package dev.atajan.kmmstonksapp.cache

import dev.atajan.kmmstonksapp.shared.cache.AppDatabase

/**
 * Make sure it is only accessible from within the multiplatform module.
 */
internal class DatabaseOperations(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
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

    /**
     * Function to get a list of all the rocket launches:
     *
     * The argument we pass to selectAllLaunchesInfo (::mapLaunchSelecting) is a function that maps the database entity class to another type.
     * In this case, we're mapping to the RocketLaunch data model class.
     */
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
                    insertTickerSymbol(stock = stock)
                }

                insertStock(stock)
            }
        }
    }

    private fun insertTickerSymbol(stock: Stock) {
        dbQuery.insertTickerSymbol(ticker = stock.tickerSymbol.ticker)
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
}