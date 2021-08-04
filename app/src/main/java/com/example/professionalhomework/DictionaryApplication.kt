package com.example.professionalhomework

import android.app.Application
import com.example.professionalhomework.di.component.AppComponent
import com.example.professionalhomework.di.component.DaggerAppComponent
import com.example.professionalhomework.rx.DefaultSchedulers
import timber.log.Timber

class DictionaryApplication : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
            .builder()
            .withContext(applicationContext)
            .withSchedulers(DefaultSchedulers)
            .build()

        Timber.plant(Timber.DebugTree())
    }

}