package com.example.professionalhomework.di.module

import android.content.Context
import androidx.room.Room
import com.example.professionalhomework.data.datasource.*
import com.example.professionalhomework.data.db.DictionaryDatabase
import com.example.professionalhomework.data.model.AppState
import com.example.professionalhomework.data.network.api.ApiService
import com.example.professionalhomework.data.repository.DictionaryRepository
import com.example.professionalhomework.data.repository.DictionaryRepositoryImpl
import com.example.professionalhomework.ui.activities.main.MainInteractor
import com.example.professionalhomework.ui.interactor.Interactor
import com.example.professionalhomework.rx.Schedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideDictionaryDatabases(context: Context): DictionaryDatabase = Room
        .databaseBuilder(context, DictionaryDatabase::class.java, "dictionary_database")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideRemoteDatasource(
        apiService: ApiService
    ): RemoteDataSource = DataSourceRemote(apiService)

    @Singleton
    @Provides
    fun provideLocalDatasource(
        db: DictionaryDatabase
    ): LocalDataSource = DataSourceLocal(db)

    @Singleton
    @Provides
    fun provideDictionaryRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): DictionaryRepository = DictionaryRepositoryImpl(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideMainInteractor(
        remoteRepository: DictionaryRepository,
        localRepository: DictionaryRepository,
        schedulers: Schedulers
    ): Interactor<AppState> = MainInteractor(remoteRepository, localRepository, schedulers)

}