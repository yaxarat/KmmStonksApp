package dev.atajan.kmmstonksapp.screen.stock_list

import dev.atajan.kmmstonksapp.viewModel.Events

internal fun Events.loadArticles() = onMainCoroutine {
    stateReducers.updateArticles()
}