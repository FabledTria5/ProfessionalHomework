package com.example.professionalhomework.data.datasource

import com.example.professionalhomework.data.network.model.Response

interface RemoteDataSource {
    suspend fun getData(languageCode: String, query: String): Response
}