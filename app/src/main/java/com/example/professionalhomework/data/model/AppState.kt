package com.example.professionalhomework.data.model

import com.example.professionalhomework.data.db.relations.WordWithMeanings

sealed class AppState {
    data class Success(val data: WordWithMeanings) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}