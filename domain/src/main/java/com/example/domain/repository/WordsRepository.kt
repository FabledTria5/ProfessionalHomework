package com.example.domain.repository

import com.example.domain.models.WordItem

interface WordsRepository {
    suspend fun getAllWords(): List<WordItem>
    suspend fun fetchWord(word: String): WordItem?
}