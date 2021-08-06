package com.example.professionalhomework.data.repository

import com.example.professionalhomework.data.datasource.LocalDataSource
import com.example.professionalhomework.data.datasource.RemoteDataSource
import com.example.professionalhomework.data.db.entities.Meaning
import com.example.professionalhomework.data.db.entities.Word
import com.example.professionalhomework.data.db.relations.WordWithMeanings

class DictionaryRepositoryImpl(
    private val remoteDatasource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    DictionaryRepository {

    override suspend fun getWord(languageCode: String, query: String) =
        remoteDatasource.getData(languageCode, query)

    override suspend fun fetchWord(word: Word, meanings: List<Meaning>) =
        localDataSource.fetchData(word, meanings)

    override suspend fun getWord(word: String) = localDataSource.getData(word)
}