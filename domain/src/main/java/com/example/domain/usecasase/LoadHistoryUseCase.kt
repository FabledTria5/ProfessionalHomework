package com.example.domain.usecasase

import com.example.domain.repository.WordsRepository

class LoadHistoryUseCase(
    private val wordsRepository: WordsRepository
) {
    suspend fun invoke() = wordsRepository.getAllWords()
}