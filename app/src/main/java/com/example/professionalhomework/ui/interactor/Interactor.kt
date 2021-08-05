package com.example.professionalhomework.ui.interactor

import com.example.professionalhomework.data.model.AppState
import io.reactivex.rxjava3.core.Single

interface Interactor<T> {
    suspend fun getWord(word: String, languageCode: String): AppState
}