package com.example.professionalhomework.di.module

import com.example.professionalhomework.ui.activities.main.MainActivity
import com.example.professionalhomework.ui.activities.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

}