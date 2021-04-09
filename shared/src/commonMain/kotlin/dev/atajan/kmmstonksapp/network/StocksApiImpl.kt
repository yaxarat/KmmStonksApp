package dev.atajan.kmmstonksapp.network

import dev.atajan.kmmstonksapp.cache.Stock
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json as KtxJson

class StocksApi {

    private val stocksApiBaseUrl  = "https://api.lil.software/stocks"

    private val client: HttpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(KtxJson {
                ignoreUnknownKeys = true // if the server sends extra fields, ignore them
            })
        }
    }

    suspend fun get(tickerSymbol: String): Stock {
        return client.get("$stocksApiBaseUrl?symbol=$tickerSymbol")
    }
}