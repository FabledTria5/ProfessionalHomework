package com.example.professionalhomework.ui.activities.splash

import moxy.MvpView
import moxy.viewstate.strategy.alias.Skip

interface SplashView : MvpView {

    @Skip
    fun startMainActivity()

}