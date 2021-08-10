package com.example.professionalhomework.di

import android.content.Context
import androidx.room.Room
import com.example.professionalhomework.BuildConfig
import com.example.professionalhomework.data.datasource.DataSourceLocal
import com.example.professionalhomework.data.datasource.DataSourceRemote
import com.example.professionalhomework.data.datasource.LocalDataSource
import com.example.professionalhomework.data.datasource.RemoteDataSource
import com.example.professionalhomework.data.db.DictionaryDatabase
import com.example.professionalhomework.data.network.api.DictionaryApi
import com.example.professionalhomework.data.repository.LocalRepository
import com.example.professionalhomework.data.repository.LocalRepositoryImpl
import com.example.professionalhomework.data.repository.RemoteRepository
import com.example.professionalhomework.data.repository.RemoteRepositoryImpl
import com.example.professionalhomework.ui.fragments.history.HistoryInteractor
import com.example.professionalhomework.ui.fragments.history.HistoryViewModel
import com.example.professionalhomework.ui.fragments.home.HomeInteractor
import com.example.professionalhomework.ui.fragments.home.HomeViewModel
import com.example.professionalhomework.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single(named("BASE_URL")) { Constants.DICTIONARY_BASE_URL }
    factory { provideOkHttpClient() }
    single(named("DictionaryRetrofit")) {
        provideDictionaryRetrofit(
            baseUrl = get(qualifier = named("BASE_URL")),
            okHttpClient = get()
        )
    }
    factory { provideDictionaryApi(retrofit = get(qualifier = named("DictionaryRetrofit"))) }
}

val localDataModule = module {
    single { provideDictionaryDatabase(context = get()) }
}

val domainModule = module {
    single<RemoteDataSource> { DataSourceRemote(dictionaryApi = get()) }
    single<LocalDataSource> { DataSourceLocal(db = get()) }

    single<LocalRepository> {
        LocalRepositoryImpl(localDataSource = get())
    }

    single<RemoteRepository> {
        RemoteRepositoryImpl(remoteDataSource = get())
    }
}

val presentationModule = module {
    single(named("HomeInteractor")) {
        HomeInteractor(
            remoteRepository = get(),
            localRepository = get()
        )
    }
    single(named("HistoryInteractor")) {
        HistoryInteractor(localRepository = get())
    }

    viewModel { HomeViewModel(interactor = get(qualifier = named("HomeInteractor"))) }
    viewModel { HistoryViewModel(historyInteractor = get(qualifier = named("HistoryInteractor"))) }
}

fun provideDictionaryDatabase(context: Context): DictionaryDatabase = Room
    .databaseBuilder(context, DictionaryDatabase::class.java, "dictionary_database")
    .fallbackToDestructiveMigration()
    .build()

fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build()
} else {
    OkHttpClient.Builder().build()
}

fun provideDictionaryApi(retrofit: Retrofit): DictionaryApi =
    retrofit.create(DictionaryApi::class.java)

fun provideDictionaryRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()