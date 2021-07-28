package com.example.professionalhomework

import com.example.professionalhomework.di.component.DaggerMainComponent
import com.example.professionalhomework.rx.DefaultSchedulers
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class MainApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<MainApplication> = DaggerMainComponent
        .builder()
        .withContext(applicationContext)
        .withSchedulers(DefaultSchedulers)
        .build()

}