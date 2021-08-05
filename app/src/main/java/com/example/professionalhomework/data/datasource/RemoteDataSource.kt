package com.example.professionalhomework.data.datasource

import com.example.professionalhomework.data.network.model.Response
import io.reactivex.rxjava3.core.Single

interface RemoteDataSource {
    suspend fun getData(languageCode: String, query: String): Response
}