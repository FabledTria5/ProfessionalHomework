package com.example.professionalhomework.data.repository

import com.example.professionalhomework.data.db.entities.Meaning
import com.example.professionalhomework.data.db.entities.Word
import com.example.professionalhomework.data.db.relations.WordWithMeanings

interface LocalRepository {
    suspend fun fetchWord(word: Word, meanings: List<Meaning>): WordWithMeanings
    suspend fun getWord(word: String): WordWithMeanings?
    suspend fun getAllWords(): List<WordWithMeanings>
}