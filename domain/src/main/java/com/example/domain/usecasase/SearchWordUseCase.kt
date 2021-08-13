package com.example.domain.usecasase

import com.example.domain.repository.WordsRepository

class SearchWordUseCase(
    private val wordsRepository: WordsRepository
) {
    suspend fun invoke(word: String) = wordsRepository.fetchWord(word = word)
}