package dev.atajan.kmmstonksapp.viewModel

import kotlin.reflect.KClass

/**
 *
 */
class StateProvider(
    stateManager: StateManager,
    events: Events
) {
    internal val stateManager by lazy { stateManager }
    internal val events by lazy { events }
}

/**
 * Screen helpers
 */

interface ScreenState // we apply this empty interface to all screen state data classes

// here we define all the screenTypes
// the AppState keeps in memory just one screenState per screenType
// in order to support dual-pane, it makes sense to have at least a MASTER and a DETAIL
enum class ScreenType{ MASTER, DETAIL, DIALOG }

// here we list all screenState classes, defining their screenType
fun getScreenType(stateClass : KClass<out ScreenState>) : ScreenType {
    return when (stateClass) {
        else -> ScreenType.MASTER // we default to MASTER
    }
}