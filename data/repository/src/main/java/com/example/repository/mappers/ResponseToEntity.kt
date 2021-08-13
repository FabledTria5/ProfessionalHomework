package com.example.repository.mappers

import com.db.models.SynonymEntity
import com.db.models.WordEntity
import com.example.network.models.DictionaryResponse
import com.example.network.models.DictionaryResponseItem

internal fun DictionaryResponseItem.toWord(word: String) = WordEntity(
    word = word,
    translation = this.meanings[0].translation.text,
    image = this.meanings[0].imageUrl
)

internal fun DictionaryResponse.toSynonyms(word: String) =
    this.filterIndexed { index, _ -> index != 0 }.map {
        SynonymEntity(
            parentWord = word,
            childWord = it.text,
            childImage = it.meanings[0].imageUrl,
            childTranslation = it.meanings[0].translation.text
        )
    }