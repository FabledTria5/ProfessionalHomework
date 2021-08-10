package com.example.professionalhomework.data.datasource

import com.example.professionalhomework.data.db.WordWithSynonyms
import com.example.professionalhomework.data.db.entities.Synonym
import com.example.professionalhomework.data.db.entities.Word

interface LocalDataSource {
    suspend fun getData(word: String): WordWithSynonyms?
    suspend fun fetchData(word: Word, synonyms: List<Synonym>): WordWithSynonyms
    suspend fun getAllWords(): List<WordWithSynonyms>
}