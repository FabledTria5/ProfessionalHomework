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
    fun insertWord(word: Word) : Completable

    @Insert
    fun insertMeanings(meanings: List<Meaning>) : Completable

    @Transaction
    @Query(value = "SELECT * FROM table_words WHERE word = :word")
    fun getWordWithMeanings(word: String): Single<WordWithMeanings>

    @Query(value = "SELECT * FROM table_words WHERE word = :word")
    fun getWords(word: String): Single<Word>

    @Query(value = "SELECT * FROM table_words_meanings WHERE parent_word = :word")
    fun getMeanings(word: String): Single<Meaning>

}