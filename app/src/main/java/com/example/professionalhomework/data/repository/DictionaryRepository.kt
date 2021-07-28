package com.example.professionalhomework.data.repository

import com.example.professionalhomework.data.db.entities.Meaning
import com.example.professionalhomework.data.db.entities.Word
import com.example.professionalhomework.data.db.relations.WordWithMeanings
import com.example.professionalhomework.data.network.model.Response
import io.reactivex.rxjava3.core.Single

interface DictionaryRepository {
    fun getWord(languageCode: String, query: String): Single<Response>
    fun getWord(word: String): Single<WordWithMeanings>
    fun fetchWord(word: Word, meanings: List<Meaning>): Single<WordWithMeanings>
}