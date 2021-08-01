package com.example.professionalhomework.di.component

import android.content.Context
import com.example.professionalhomework.DictionaryApplication
import com.example.professionalhomework.di.module.DataModule
import com.example.professionalhomework.di.module.NetworkModule
import com.example.professionalhomework.di.module.viewmodel.ViewModelModule
import com.example.professionalhomework.rx.Schedulers
import com.example.professionalhomework.ui.activities.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        DataModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withSchedulers(schedulers: Schedulers): Builder

        fun build(): AppComponent
    }

    fun inject(application: DictionaryApplication)

    fun inject(activity: MainActivity)

}