package com.example.professionalhomework.data.repository

import com.example.professionalhomework.data.db.WordWithSynonyms
import com.example.professionalhomework.data.db.entities.Synonym
import com.example.professionalhomework.data.db.entities.Word

interface LocalRepository {
    suspend fun fetchWord(word: Word, meanings: List<Synonym>): WordWithSynonyms
    suspend fun getWord(word: String): WordWithSynonyms?
    suspend fun getAllWords(): List<WordWithSynonyms>
}