package com.example.professionalhomework.data.datasource

import com.example.professionalhomework.data.network.api.DictionaryApi

class DataSourceRemote(
    private val dictionaryApi: DictionaryApi,
) : RemoteDataSource {

    override suspend fun getData(query: String) =
        dictionaryApi.searchWord(query)
}