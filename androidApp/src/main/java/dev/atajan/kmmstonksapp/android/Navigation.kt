package dev.atajan.kmmstonksapp.android

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import dev.atajan.kmmstonksapp.android.screen.stock_list.StockDetailScreen
import dev.atajan.kmmstonksapp.android.screen.stock_list.StockListScreen
import dev.atajan.kmmstonksapp.screen.stock_detail.getStockDetailState
import dev.atajan.kmmstonksapp.screen.stock_list.getStockListState
import dev.atajan.kmmstonksapp.viewModel.KMPViewModel

@Composable
fun Navigation(model: KMPViewModel) {

    val events = model.events
    val appState by model.stateFlow.collectAsState()
    val stateProvider = appState.getStateProvider(model)
    val navController = rememberNavController()

    Log.d("Stonks App","recomposition Index: "+appState.recompositionIndex.toString())

    NavHost(
        navController = navController,
        startDestination = "stock_list"
    ) {
        composable(route = "stock_list") {
            StockListScreen(
                stockListState = stateProvider.getStockListState(),
                events = events,
                onListItemClick = { navController.navigate("stock/$it") }
            )
        }
        composable("stock/{item}") { backStackEntry ->
            val item = backStackEntry.arguments?.getString("item")!!
            StockDetailScreen(
                stockDetailState = stateProvider.getStockDetailState(item)
            )
        }
    }

}