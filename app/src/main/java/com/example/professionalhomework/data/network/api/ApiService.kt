package com.example.professionalhomework.data.network.api

import com.example.professionalhomework.data.network.model.Response
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(value = "entries/{language_code}/{word}")
    suspend fun getWord(
        @Path(value = "language_code") languageCode: String,
        @Path(value = "word") word: String
    ) : Response

}