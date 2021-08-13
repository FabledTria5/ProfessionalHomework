package com.example.professionalhomework

import android.app.Application
import com.db.di.dbModule
import com.example.network.di.networkModule
import com.example.professionalhomework.presentation.di.mainModule
import com.example.professionalhomework.presentation.di.useCaseModule
import com.example.repository.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class DictionaryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        startKoin {
            val modules =
                listOf(networkModule, dbModule, repositoryModule, useCaseModule, mainModule)
            androidLogger(Level.NONE)
            androidContext(this@DictionaryApplication)
            modules(modules)
        }
    }
}