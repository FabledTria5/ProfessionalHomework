package com.example.domain.models

data class WordItem(
    val word: String,
    val translation: String,
    val imagePath: String,
    val synonyms: List<Pair<String, String>>
)
