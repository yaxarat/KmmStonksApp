package dev.atajan.kmmstonksapp.android

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import dev.atajan.kmmstonksapp.android.screen.article_list.StockListScreen
import dev.atajan.kmmstonksapp.cache.Repository
import dev.atajan.kmmstonksapp.screen.stock_list.StockListState
import dev.atajan.kmmstonksapp.screen.stock_list.getStockListState
import dev.atajan.kmmstonksapp.viewModel.Events
import dev.atajan.kmmstonksapp.viewModel.KMPViewModel
import dev.atajan.kmmstonksapp.viewModel.StateManager
import dev.atajan.kmmstonksapp.viewModel.StateReducers

@Composable
fun Navigation(model: KMPViewModel) {

    val events = model.events
    val appState by model.stateFlow.collectAsState()
    Log.d("D-KMP-SAMPLE","recomposition Index: "+appState.recompositionIndex.toString())
    val stateProvider = appState.getStateProvider(model)


    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "stock_list"
    ) {
        composable(route = "stock_list") {
            StockListScreen(
                stockListState = stateProvider.getStockListState(),
                events = events,
                onListItemClick = {}
            )
        }
//        composable("country/{item}") { backStackEntry ->
//            val item = backStackEntry.arguments?.getString("item")!!
//            CountryDetailScreen(
//                countryDetailState = stateProvider.getCountryDetailState(item)
//            )
//        }
    }

}