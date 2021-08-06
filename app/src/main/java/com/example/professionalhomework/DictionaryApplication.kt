package com.example.professionalhomework

import android.app.Application
import com.example.professionalhomework.di.localDataModule
import com.example.professionalhomework.di.domainModule
import com.example.professionalhomework.di.networkModule
import com.example.professionalhomework.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class DictionaryApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DictionaryApplication)
            modules(listOf(presentationModule, domainModule, localDataModule, networkModule))
        }

        Timber.plant(Timber.DebugTree())
    }

}