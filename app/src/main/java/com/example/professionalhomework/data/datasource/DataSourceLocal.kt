package com.example.professionalhomework.data.datasource

import com.example.professionalhomework.data.db.DictionaryDatabase
import com.example.professionalhomework.data.db.WordWithSynonyms
import com.example.professionalhomework.data.db.entities.Synonym
import com.example.professionalhomework.data.db.entities.Word

class DataSourceLocal(
    db: DictionaryDatabase
) : LocalDataSource {

    private val wordsDao = db.wordsDao()

    override suspend fun fetchData(word: Word, synonyms: List<Synonym>): WordWithSynonyms {
        wordsDao.insertWord(word = word)
        wordsDao.insertSynonyms(synonyms = synonyms)
        return wordsDao.getWordWithSynonyms(word = word.word)
    }

    override suspend fun getAllWords(): List<WordWithSynonyms> = wordsDao.getAllWords()

    override suspend fun getData(word: String) = wordsDao.getWordWithSynonyms(word)

}