package com.example.professionalhomework.presentation.entities

import com.example.domain.models.WordItem

sealed class AppState {
    data class SearchWordSuccess(val data: WordItem) : AppState()
    data class LoadHistorySuccess(val data: List<WordItem>): AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}