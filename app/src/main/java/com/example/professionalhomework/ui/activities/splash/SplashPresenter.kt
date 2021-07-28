package com.example.professionalhomework.ui.activities.splash

import dagger.assisted.AssistedInject
import moxy.MvpPresenter

class SplashPresenter @AssistedInject constructor(): MvpPresenter<SplashView>() {
    fun onAnimationEnd() = viewState.startMainActivity()
}