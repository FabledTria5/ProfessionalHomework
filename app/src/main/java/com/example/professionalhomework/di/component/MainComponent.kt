package com.example.professionalhomework.di.component

import android.content.Context
import com.example.professionalhomework.MainApplication
import com.example.professionalhomework.di.module.DataModule
import com.example.professionalhomework.di.module.NetworkModule
import com.example.professionalhomework.di.module.UiModule
import com.example.professionalhomework.rx.Schedulers
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        UiModule::class,
        NetworkModule::class,
        DataModule::class
    ]
)
interface MainComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context) : Builder

        @BindsInstance
        fun withSchedulers(schedulers: Schedulers) : Builder

        fun build(): MainComponent

    }

}