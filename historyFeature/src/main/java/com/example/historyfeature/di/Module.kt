package com.example.historyfeature.di

import com.example.domain.usecasase.LoadHistoryUseCase
import com.example.historyfeature.ui.HistoryFragment
import com.example.historyfeature.ui.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectDependencies() = loadHistoryModule

val historyModule = module {
    scope<HistoryFragment> {
        scoped { LoadHistoryUseCase(wordsRepository = get()) }
        viewModel { (HistoryViewModel(loadHistoryUseCase = get())) }
    }
}

val loadHistoryModule by lazy { loadKoinModules(historyModule) }