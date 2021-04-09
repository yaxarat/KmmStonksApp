package dev.atajan.kmmstonksapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dev.atajan.kmmstonksapp.android.styling.KmmStonksAppTheme

class MainActivity : ComponentActivity() {

    private val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val coreModel = appViewModel.getCoreViewModel(applicationContext)
        setContent {
            KmmStonksAppTheme {
                Navigation(coreModel)
            }
        }
    }

}
