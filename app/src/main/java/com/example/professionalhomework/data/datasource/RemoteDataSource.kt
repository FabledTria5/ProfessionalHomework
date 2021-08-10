package com.example.professionalhomework.data.datasource

import com.example.professionalhomework.data.network.model.DictionaryResponse

interface RemoteDataSource {
    suspend fun getData(query: String): DictionaryResponse
}