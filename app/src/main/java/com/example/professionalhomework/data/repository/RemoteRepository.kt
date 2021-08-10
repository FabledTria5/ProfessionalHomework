package com.example.professionalhomework.data.repository

import com.example.professionalhomework.data.network.model.DictionaryResponse

interface RemoteRepository {
    suspend fun getData(query: String): DictionaryResponse
}