package com.example.professionalhomework.data.datasource

import com.example.professionalhomework.data.db.entities.Meaning
import com.example.professionalhomework.data.db.entities.Word
import com.example.professionalhomework.data.db.relations.WordWithMeanings

interface LocalDataSource {
    suspend fun getData(word: String): WordWithMeanings?
    suspend fun fetchData(word: Word, meanings: List<Meaning>): WordWithMeanings
    suspend fun getAllWords(): List<WordWithMeanings>
}