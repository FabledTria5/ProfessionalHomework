package com.example.network.di

import com.example.domain.utils.Constants.DICTIONARY_BASE_URL
import com.example.network.DictionaryApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(DICTIONARY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
            .create(DictionaryApi::class.java)
    }
}

fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build()
} else {
    OkHttpClient.Builder().build()
}
