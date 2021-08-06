package com.example.professionalhomework.data.repository

import com.example.professionalhomework.data.datasource.RemoteDataSource
import com.example.professionalhomework.data.network.model.Response

class RemoteRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : RemoteRepository {

    override suspend fun getData(languageCode: String, query: String) =
        remoteDataSource.getData(languageCode = languageCode, query = query)

}