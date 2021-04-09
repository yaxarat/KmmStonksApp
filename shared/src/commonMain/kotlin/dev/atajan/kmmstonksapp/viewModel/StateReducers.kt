package dev.atajan.kmmstonksapp.viewModel

import dev.atajan.kmmstonksapp.cache.Repository

class StateReducers (stateManager : StateManager, repo : Repository) {

    internal val stateManager by lazy { stateManager }

    internal val dataRepository by lazy { repo }

}