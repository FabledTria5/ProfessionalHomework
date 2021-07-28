package com.example.professionalhomework.data.datasource

import com.example.professionalhomework.data.db.DictionaryDatabase
import com.example.professionalhomework.data.db.entities.Meaning
import com.example.professionalhomework.data.db.entities.Word
import com.example.professionalhomework.data.db.relations.WordWithMeanings
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DataSourceLocal @Inject constructor(
    db: DictionaryDatabase
) : LocalDataSource {

    private val wordsDao = db.wordsDao()

    override fun fetchData(word: Word, meanings: List<Meaning>): Single<WordWithMeanings> =
        wordsDao.insertWord(word = word)
            .andThen(wordsDao.insertMeanings(meanings))
            .andThen(wordsDao.getWordWithMeanings(word.word))

    override fun getData(word: String): Single<WordWithMeanings> =
        wordsDao.getWordWithMeanings(word)

}