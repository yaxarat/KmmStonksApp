package dev.atajan.kmmstonksapp.viewModel

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.reflect.KClass

class StateManager {
    internal val mutableStateFlow = MutableStateFlow(AppState())

    val screenStatesMap : MutableMap<ScreenType, ScreenState> = mutableMapOf()
    val screenScopesMap : MutableMap<ScreenType, CoroutineScope> = mutableMapOf()

    // only called by the State Providers
    inline fun <reified T:ScreenState> getScreen(
        initState: () -> T,
        callOnInit: () -> Unit,
        reinitWhen: (T) -> Boolean = { false },
    ) : T {
        val screenType = getScreenType(T::class)
        val currentState = screenStatesMap[screenType] as? T

        if (currentState == null || reinitWhen(currentState)) {
            screenScopesMap[screenType]?.cancel()
            screenScopesMap[screenType] = CoroutineScope(Job() + Dispatchers.Main)

            val initializedState = initState()
            screenStatesMap[screenType] = initializedState
            callOnInit()
            return initializedState
        }

        return currentState
    }


    // only called by the State Reducers
    inline fun <reified T:ScreenState> updateScreen(
        stateClass: KClass<T>,
        update: (T) -> T,
    ) {
        //debugLogger.log("updateScreen: "+T::class.simpleName)
        val screenType = getScreenType(stateClass)
        val currentState = screenStatesMap[screenType] as? T
        if (currentState != null) { // only perform update if the state class object is currently inside the screenStatesMap
            screenStatesMap[screenType] = update(currentState)
            triggerRecomposition()
        }
    }

    fun triggerRecomposition() {
        mutableStateFlow.value = AppState(mutableStateFlow.value.recompositionIndex + 1)
    }

    fun getScreenCoroutineScope(stateClass : KClass<out ScreenState>) : CoroutineScope? {
        val screenType = getScreenType(stateClass)
        return screenScopesMap[screenType]
    }
}

data class AppState (val recompositionIndex: Int = 0) {
    fun getStateProvider(model : KMPViewModel) : StateProvider {
        return model.stateProvider
    }
}