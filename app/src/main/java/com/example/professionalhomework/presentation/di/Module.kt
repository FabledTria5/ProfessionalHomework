package com.example.professionalhomework.presentation.di

import com.example.domain.usecasase.SearchWordUseCase
import com.example.professionalhomework.presentation.fragments.home.HomeFragment
import com.example.professionalhomework.presentation.fragments.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal fun injectDependencies() = loadHomeModule

internal val homeModule = module {
    scope<HomeFragment> {
        scoped { SearchWordUseCase(wordsRepository = get()) }
        viewModel { HomeViewModel(searchWordUseCase = get()) }
    }
}

internal val loadHomeModule by lazy { loadKoinModules(homeModule) }