package com.example.professionalhomework.data.network.api

import com.example.professionalhomework.data.network.model.DictionaryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DictionaryApi {

    @GET(value = "search")
    suspend fun searchWord(
        @Query(value = "search") query: String,
        @Query(value = "page") page: Int = 1
    ): DictionaryResponse

}