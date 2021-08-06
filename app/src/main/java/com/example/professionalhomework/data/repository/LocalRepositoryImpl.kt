package com.example.professionalhomework.data.repository

import com.example.professionalhomework.data.datasource.LocalDataSource
import com.example.professionalhomework.data.db.entities.Meaning
import com.example.professionalhomework.data.db.entities.Word
import com.example.professionalhomework.data.db.relations.WordWithMeanings

class LocalRepositoryImpl(
    private val localDataSource: LocalDataSource
) : LocalRepository {

    override suspend fun fetchWord(word: Word, meanings: List<Meaning>) =
        localDataSource.fetchData(word, meanings)

    override suspend fun getWord(word: String) = localDataSource.getData(word)

    override suspend fun getAllWords() = localDataSource.getAllWords()
}