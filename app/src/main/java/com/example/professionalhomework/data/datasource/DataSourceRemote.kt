package com.example.professionalhomework.data.datasource

import com.example.professionalhomework.data.network.api.ApiService
import javax.inject.Inject

class DataSourceRemote @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {

    override fun getData(languageCode: String, query: String) =
        apiService.getWord(languageCode, query)
}