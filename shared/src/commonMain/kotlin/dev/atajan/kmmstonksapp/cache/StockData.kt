package dev.atajan.kmmstonksapp.cache

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stock (
    @SerialName("open")
    val open: Double,
    @SerialName("high")
    val high: Double,
    @SerialName("low")
    val low: Double,
    @SerialName("current")
    val current: Double,
    @SerialName("previous_close")
    val previousClose: Double,
    @SerialName("name")
    val name: String,
    @SerialName("ticker_symbol")
    val tickerSymbol: Ticker = Ticker(""),
)

@Serializable
data class Ticker(
    @SerialName("ticker")
    val ticker: String,
)