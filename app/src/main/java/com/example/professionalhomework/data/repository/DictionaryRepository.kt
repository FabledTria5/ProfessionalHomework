package com.example.professionalhomework.data.repository

import com.example.professionalhomework.data.db.entities.Meaning
import com.example.professionalhomework.data.db.entities.Word
import com.example.professionalhomework.data.db.relations.WordWithMeanings
import com.example.professionalhomework.data.network.model.Response

interface DictionaryRepository {
    suspend fun getWord(languageCode: String, query: String): Response
    suspend fun getWord(word: String): WordWithMeanings?
    suspend fun fetchWord(word: Word, meanings: List<Meaning>): WordWithMeanings
}