package com.example.repository.di

import com.example.domain.repository.WordsRepository
import com.example.repository.data_source.WordsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<WordsRepository> { WordsRepositoryImpl(dictionaryApi = get(), db = get()) }
}