package com.example.professionalhomework.ui.activities.splash

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface SplashView : MvpView {

    @AddToEndSingle
    fun setupAnimation()

    @Skip
    fun startMainActivity()

}