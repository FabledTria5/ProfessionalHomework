package com.example.professionalhomework.data.repository

import com.example.professionalhomework.data.network.model.Response

interface RemoteRepository {
    suspend fun getData(languageCode: String, query: String): Response
}