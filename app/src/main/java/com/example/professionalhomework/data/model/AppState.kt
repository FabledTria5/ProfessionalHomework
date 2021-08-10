package com.example.professionalhomework.data.model

import com.example.professionalhomework.data.db.WordWithSynonyms

sealed class AppState {
    data class Success(val data: WordWithSynonyms) : AppState()
    data class ListSuccess(val data: List<WordWithSynonyms>): AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}