package dev.atajan.kmmstonksapp.viewModel

import dev.atajan.kmmstonksapp.screen.stock_detail.StockDetailState
import dev.atajan.kmmstonksapp.screen.stock_list.StockListState
import kotlin.reflect.KClass

/**
 * State provider class that provides access to [StateManager] and [Events]
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

/**
 * This empty interface is applied to all screen state data classes
 */
interface ScreenState

/**
 *  Define all the screenTypes.
 *  The AppState keeps in memory just one screenState per screenType in order to support dual-pane.
 *  It makes sense to have at least a MASTER and a DETAIL
 */
enum class ScreenType{ MASTER, DETAIL }

/**
 * List all screenState classes, defining their screenType
 */
fun getScreenType(stateClass : KClass<out ScreenState>) : ScreenType {
    return when (stateClass) {
        StockListState::class -> ScreenType.MASTER
        StockDetailState::class -> ScreenType.DETAIL
        else -> ScreenType.MASTER
    }
}