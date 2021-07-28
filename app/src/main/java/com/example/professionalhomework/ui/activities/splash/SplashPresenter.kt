package com.example.professionalhomework.ui.activities.splash

import dagger.assisted.AssistedInject
import moxy.MvpPresenter

class SplashPresenter @AssistedInject constructor(): MvpPresenter<SplashView>() {

    fun onCreate() = viewState.setupAnimation()

    fun onAnimationEnd() = viewState.startMainActivity()
}