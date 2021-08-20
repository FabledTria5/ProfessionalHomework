package com.example.repository.mappers

import com.db.relations.WordWithSynonyms
import com.example.domain.models.WordItem

internal fun List<WordWithSynonyms>.toDomain() = this.map {
    WordItem(
        word = it.word.word,
        translation = it.word.translation,
        imagePath = it.word.image,
        synonyms = it.synonyms.map { synonym ->
            Pair(
                synonym.childWord,
                synonym.childTranslation
            )
        }
    )
}

internal fun WordWithSynonyms.toDomain() = WordItem(
    word = word.word,
    translation = word.translation,
    imagePath = "https:${word.image}",
    synonyms = synonyms.map { Pair(it.childWord, it.childTranslation) })