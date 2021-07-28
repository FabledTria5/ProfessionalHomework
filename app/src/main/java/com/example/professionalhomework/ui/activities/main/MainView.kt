package com.example.professionalhomework.ui.activities.main

import com.example.professionalhomework.data.db.entities.Word
import com.example.professionalhomework.data.model.AppState
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface MainView : MvpView {

    @AddToEndSingle
    fun updateWord(word: Word)

    @AddToEndSingle
    fun updateState(state: AppState)

    @AddToEndSingle
    fun enableAudio()

    @AddToEndSingle
    fun disableAudio()

    @AddToEndSingle
    fun hideLoading()

    @AddToEndSingle
    fun showError()

    @Skip
    fun playWord(wordPronunciation: String)
}