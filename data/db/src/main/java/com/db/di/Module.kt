package com.db.di

import androidx.room.Room
import com.db.DictionaryDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {
    single {
        Room
            .databaseBuilder(
                androidApplication(),
                DictionaryDatabase::class.java,
                "dictionary_database"
            )
            .fallbackToDestructiveMigration()
            .build()
    }
}