package dev.atajan.kmmstonksapp.viewModel

import dev.atajan.kmmstonksapp.cache.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class KMPViewModel (repo: Repository) {

    val stateFlow: StateFlow<AppState>
        get() = stateManager.mutableStateFlow

    private val stateManager by lazy { StateManager() }

    private val stateReducers by lazy { StateReducers(stateManager, repo) }

    val events by lazy { Events(stateReducers) }

    internal val stateProvider by lazy { StateProvider(stateManager, events) }

    companion object Factory {
        // factory methods are defined in the platform-specific shared code
    }
}

class Events (stateReducers: StateReducers) {
    internal val stateReducers by lazy { stateReducers }

    // we run each event function on a Dispatchers.Main coroutine, scoped on the specific screen
    fun screenCoroutine(
        stateClass: KClass<out ScreenState>,
        block: suspend () -> Unit
    ) {
        stateReducers.stateManager.getScreenCoroutineScope(stateClass)?.let {
            it.launch {
                block()
            }
        }
    }
}