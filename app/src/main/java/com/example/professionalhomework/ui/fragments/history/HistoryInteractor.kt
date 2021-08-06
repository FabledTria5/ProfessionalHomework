package com.example.professionalhomework.ui.fragments.history

import com.example.professionalhomework.data.model.AppState
import com.example.professionalhomework.data.repository.LocalRepository

class HistoryInteractor(
    private val localRepository: LocalRepository,
) {

    suspend fun getAllWords() = AppState.ListSuccess(localRepository.getAllWords())

}