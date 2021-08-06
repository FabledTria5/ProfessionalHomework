package com.example.professionalhomework.ui.interactor

import com.example.professionalhomework.data.model.AppState

interface Interactor<T> {
    suspend fun getWord(word: String, languageCode: String): AppState
}