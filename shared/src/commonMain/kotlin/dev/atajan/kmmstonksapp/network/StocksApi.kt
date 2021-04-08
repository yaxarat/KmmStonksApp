package dev.atajan.kmmstonksapp.network

import dev.atajan.kmmstonksapp.cache.Stock

interface StocksApi {
    suspend fun get(tickerSymbol: String): Stock
}