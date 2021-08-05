package com.example.professionalhomework.data.db

import androidx.room.*
import com.example.professionalhomework.data.db.entities.Meaning
import com.example.professionalhomework.data.db.entities.Word
import com.example.professionalhomework.data.db.relations.WordWithMeanings
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface WordsDao {

    @Insert
    suspend fun insertWord(word: Word)

    @Insert
    suspend fun insertMeanings(meanings: List<Meaning>)

    @Transaction
    @Query(value = "SELECT * FROM table_words WHERE word = :word")
    suspend fun getWordWithMeanings(word: String): WordWithMeanings

    @Query(value = "SELECT * FROM table_words WHERE word = :word")
    suspend fun getWords(word: String): Word

    @Query(value = "SELECT * FROM table_words_meanings WHERE parent_word = :word")
    suspend fun getMeanings(word: String): Meaning

}