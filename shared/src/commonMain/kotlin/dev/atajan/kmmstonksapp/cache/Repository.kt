package dev.atajan.kmmstonksapp.cache

import com.squareup.sqldelight.db.SqlDriver
import dev.atajan.kmmstonksapp.network.StocksApi
import dev.atajan.kmmstonksapp.shared.cache.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository (val useDefaultDispatcher : Boolean, val sqlDriver : SqlDriver) {

    internal val appDatabase by lazy { AppDatabase(sqlDriver) }
    internal val stockApi by lazy { StocksApi() }
//    val localSettings by lazy { MySettings(settings) }
//    internal val runtimeCache by lazy { CacheObjects() }


    // if useDefaultDispatcher is TRUE, we run repository functions in Dispatchers.Default
    // otherwise, we run them in the originating coroutine dispatcher, which is Dispatchers.Main
    // NOTE: currently we are passing useDefaultDispatcher=TRUE only for Android
    // on iOS we will wait for the new Kotlin/Native memory model to become available
    suspend fun <T> withRepoContext (block: suspend () -> T) : T {
        return if (useDefaultDispatcher) {
            withContext(Dispatchers.Default) {
                block()
            }
        } else {
            block()
        }
    }

}