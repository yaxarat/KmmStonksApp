package dev.atajan.kmmstonksapp.android

import android.content.Context
import androidx.lifecycle.ViewModel
import dev.atajan.kmmstonksapp.getAndroidInstance
import dev.atajan.kmmstonksapp.viewModel.KMPViewModel

class AppViewModel : ViewModel() {

    private lateinit var coreModel : KMPViewModel

    fun getCoreViewModel(context : Context) : KMPViewModel {
        if (!this::coreModel.isInitialized) {
            coreModel = KMPViewModel.Factory.getAndroidInstance(context)
        }
        return coreModel
    }
}