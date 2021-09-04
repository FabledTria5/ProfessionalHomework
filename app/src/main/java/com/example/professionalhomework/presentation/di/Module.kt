package com.example.professionalhomework.presentation.di

import com.example.domain.usecasase.LoadHistoryUseCase
import com.example.domain.usecasase.SearchWordUseCase
import com.example.professionalhomework.presentation.fragments.history.HistoryViewModel
import com.example.professionalhomework.presentation.fragments.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    single { SearchWordUseCase(wordsRepository = get()) }
    single { LoadHistoryUseCase(wordsRepository = get()) }
}

val mainModule = module {
    viewModel { HomeViewModel(searchWordUseCase = get()) }
    viewModel { HistoryViewModel(loadHistoryUseCase = get()) }
}