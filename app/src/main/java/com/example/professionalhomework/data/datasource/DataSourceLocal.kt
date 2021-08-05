package com.example.professionalhomework.data.datasource

import com.example.professionalhomework.data.db.DictionaryDatabase
import com.example.professionalhomework.data.db.entities.Meaning
import com.example.professionalhomework.data.db.entities.Word
import com.example.professionalhomework.data.db.relations.WordWithMeanings
import javax.inject.Inject

class DataSourceLocal @Inject constructor(
    db: DictionaryDatabase
) : LocalDataSource {

    private val wordsDao = db.wordsDao()

    override suspend fun fetchData(word: Word, meanings: List<Meaning>): WordWithMeanings {
        wordsDao.insertWord(word = word)
        wordsDao.insertMeanings(meanings = meanings)
        return wordsDao.getWordWithMeanings(word = word.word)
    }

    override suspend fun getData(word: String): WordWithMeanings =
        wordsDao.getWordWithMeanings(word)

}