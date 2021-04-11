package dev.atajan.kmmstonksapp

import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import dev.atajan.kmmstonksapp.cache.Repository
import dev.atajan.kmmstonksapp.shared.cache.AppDatabase
import dev.atajan.kmmstonksapp.viewModel.AppState
import dev.atajan.kmmstonksapp.viewModel.KMPViewModel
import io.ktor.utils.io.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun KMPViewModel.Factory.getIosInstance() : KMPViewModel {
    val sqlDriver = NativeSqliteDriver(AppDatabase.Schema, "stock.db")
    val repository = Repository(false, sqlDriver)
    return KMPViewModel(repository)
}


// this is required, because default arguments of Kotlin functions are currently not exposed to Objective-C or Swift
// https://youtrack.jetbrains.com/issue/KT-41908
fun KMPViewModel.getDefaultAppState() : AppState {
    return AppState()
}

// this function notifies of any state changes to the iOS AppViewModel class
// hopefully this code will eventually be provided by an official Kotlin function
// https://youtrack.jetbrains.com/issue/KT-41953
fun KMPViewModel.onChange(provideNewState: ((AppState) -> Unit)) : Closeable {

    val job = Job()

    stateFlow.onEach {
        provideNewState(it)
    }.launchIn(
        CoroutineScope(Dispatchers.Main + job)
    )

    return object : Closeable {
        override fun close() {
            job.cancel()
        }
    }
}