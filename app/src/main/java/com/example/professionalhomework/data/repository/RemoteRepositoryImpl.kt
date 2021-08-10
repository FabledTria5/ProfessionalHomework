package com.example.professionalhomework.data.repository

import com.example.professionalhomework.data.datasource.RemoteDataSource

class RemoteRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : RemoteRepository {

    override suspend fun getData(query: String) =
        remoteDataSource.getData(query = query)

}