package com.example.professionalhomework.data.repository

import com.example.professionalhomework.data.db.entities.Meaning
import com.example.professionalhomework.data.db.entities.Word
import com.example.professionalhomework.data.network.model.DictionaryResponse

object DataConverter {

    fun convertToMeanings(query: String, response: DictionaryResponse): List<Meaning> {
        val meanings = mutableListOf<Meaning>()
        for (meaning in response.meanings) {
            for (definition in meaning.definitions) {
                meanings.add(Meaning(query, meaning.partOfSpeech, definition.definition))
            }
        }
        return meanings
    }

    fun convertToWord(word: String, audio: String?) =
        Word(word, audio)
}