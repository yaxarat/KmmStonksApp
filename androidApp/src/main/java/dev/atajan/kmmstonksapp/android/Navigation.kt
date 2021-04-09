package dev.atajan.kmmstonksapp.android

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import dev.atajan.kmmstonksapp.viewModel.KMPViewModel

@Composable
fun Navigation(model: KMPViewModel) {

    val events = model.events
    val appState by model.stateFlow.collectAsState()
    Log.d("D-KMP-SAMPLE","recomposition Index: "+appState.recompositionIndex.toString())
    val stateProvider = appState.getStateProvider(model)


    val navController = rememberNavController()

    NavHost(navController, startDestination = "countrieslist") {
//        composable("countrieslist") {
//            CountriesListScreen(
//                countriesListState = stateProvider.getCountriesListState(),
//                events = events,
//                onListItemClick = { navController.navigate("country/$it") },
//            )
//        }
//        composable("country/{item}") { backStackEntry ->
//            val item = backStackEntry.arguments?.getString("item")!!
//            CountryDetailScreen(
//                countryDetailState = stateProvider.getCountryDetailState(item)
//            )
//        }
    }

}