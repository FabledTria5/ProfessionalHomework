package com.example.historyfeature.di

import com.example.domain.usecasase.LoadHistoryUseCase
import com.example.historyfeature.ui.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val historyModule = module {
    single { LoadHistoryUseCase(wordsRepository = get()) }
    viewModel { (HistoryViewModel(loadHistoryUseCase = get())) }
}

val loadHistoryModule by lazy { loadKoinModules(historyModule) }