package com.example.professionalhomework.data.db

import androidx.room.*
import com.example.professionalhomework.data.db.entities.Synonym
import com.example.professionalhomework.data.db.entities.Word

@Dao
interface WordsDao {

    @Insert
    suspend fun insertWord(word: Word)

    @Insert
    suspend fun insertSynonyms(synonyms: List<Synonym>)

    @Transaction
    @Query(value = "SELECT * FROM words_table WHERE word = :word")
    suspend fun getWordWithSynonyms(word: String): WordWithSynonyms

    @Query(value = "SELECT * FROM words_table")
    suspend fun getAllWords(): List<WordWithSynonyms>

}