package dev.atajan.kmmstonksapp.viewModel

class StateProvider (stateManager : StateManager, events : Events) {

    internal val stateManager by lazy { stateManager }

    internal val events by lazy { events }

}