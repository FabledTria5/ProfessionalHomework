package com.db.daos

import androidx.room.*
import com.db.models.SynonymEntity
import com.db.models.WordEntity
import com.db.relations.WordWithSynonyms

@Dao
interface WordsDao {

    @Insert
    suspend fun insertWord(word: WordEntity)

    @Insert
    suspend fun insertSynonyms(synonyms: List<SynonymEntity>)

    @Transaction
    @Query(value = "SELECT * FROM words_table WHERE word = :word")
    suspend fun getWordWithSynonyms(word: String): WordWithSynonyms?

    @Query(value = "SELECT * FROM words_table")
    suspend fun getAllWords(): List<WordWithSynonyms>

}