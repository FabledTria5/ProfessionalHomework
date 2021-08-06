package com.example.professionalhomework.di.module

import android.content.Context
import androidx.room.Room
import com.example.professionalhomework.BuildConfig
import com.example.professionalhomework.data.datasource.DataSourceLocal
import com.example.professionalhomework.data.datasource.DataSourceRemote
import com.example.professionalhomework.data.datasource.LocalDataSource
import com.example.professionalhomework.data.datasource.RemoteDataSource
import com.example.professionalhomework.data.db.DictionaryDatabase
import com.example.professionalhomework.data.model.AppState
import com.example.professionalhomework.data.network.api.ApiService
import com.example.professionalhomework.data.repository.DictionaryRepository
import com.example.professionalhomework.data.repository.DictionaryRepositoryImpl
import com.example.professionalhomework.ui.activities.main.MainInteractor
import com.example.professionalhomework.ui.activities.main.MainViewModel
import com.example.professionalhomework.ui.interactor.Interactor
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
    single { provideRetrofit(baseUrl = get(qualifier = named("BASE_URL")), okHttpClient = get()) }
    factory { provideDictionaryApi(retrofit = get()) }
}

val localDataModule = module {
    single { provideDictionaryDatabase(context = get()) }
}

val domainModule = module {
    single<RemoteDataSource> { DataSourceRemote(apiService = get()) }
    single<LocalDataSource> { DataSourceLocal(db = get()) }

    single<DictionaryRepository> {
        DictionaryRepositoryImpl(
            remoteDatasource = get(),
            localDataSource = get()
        )
    }
}

val presentationModule = module {
    single<Interactor<AppState>> {
        MainInteractor(
            remoteRepository = get(),
            localRepository = get()
        )
    }
    viewModel { MainViewModel(interactor = get()) }
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

fun provideDictionaryApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .build()