package com.example.professionalhomework.data.repository

import com.example.professionalhomework.data.db.entities.Synonym
import com.example.professionalhomework.data.db.entities.Word
import com.example.professionalhomework.data.network.model.DictionaryResponse
import com.example.professionalhomework.data.network.model.DictionaryResponseItem

object DataMapper {

    fun mapResponseToWord(dictionaryResponseItem: DictionaryResponseItem) = Word(
        word = dictionaryResponseItem.text,
        translation = dictionaryResponseItem.meanings[0].translation.text,
        image = dictionaryResponseItem.meanings[0].imageUrl,
        sound = dictionaryResponseItem.meanings[0].soundUrl,
    )

    fun mapResponseToSynonyms(dictionaryResponse: DictionaryResponse): List<Synonym> =
        dictionaryResponse.filterIndexed { index, _ -> index != 0 }.map {
            Synonym(
                parentWord = dictionaryResponse[0].text,
                childWord = it.text,
                childImage = it.meanings[0].imageUrl,
                childSound = it.meanings[0].soundUrl,
                childTranslation = it.meanings[0].translation.text
            )
        }

}