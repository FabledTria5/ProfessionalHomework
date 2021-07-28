package com.example.professionalhomework.ui.activities.splash

import dagger.assisted.AssistedFactory

@AssistedFactory
interface SplashPresenterFactory {
    fun create(): SplashPresenter
}