package dev.atajan.kmmstonksapp

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import dev.atajan.kmmstonksapp.cache.Repository
import dev.atajan.kmmstonksapp.shared.cache.AppDatabase
import dev.atajan.kmmstonksapp.viewModel.KMPViewModel

fun KMPViewModel.Factory.getAndroidInstance(context : Context) : KMPViewModel {
    val sqlDriver = AndroidSqliteDriver(
        schema = AppDatabase.Schema,
        context = context,
        name = "stock.db"
    )
    val repository = Repository(
        useDefaultDispatcher = true,
        sqlDriver = sqlDriver
    )

    return KMPViewModel(repository)
}
