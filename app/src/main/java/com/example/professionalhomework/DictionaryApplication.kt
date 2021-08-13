package com.example.professionalhomework

import android.app.Application
import com.example.professionalhomework.di.module.localDataModule
import com.example.professionalhomework.di.module.domainModule
import com.example.professionalhomework.di.module.networkModule
import com.example.professionalhomework.di.module.presentationModule
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