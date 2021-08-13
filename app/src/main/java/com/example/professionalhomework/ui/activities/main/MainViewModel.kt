package com.example.professionalhomework.ui.activities.main

import androidx.lifecycle.viewModelScope
import com.example.professionalhomework.data.model.AppState
import com.example.professionalhomework.ui.base.viewmodel.BaseViewModel
import com.example.professionalhomework.ui.interactor.Interactor
import com.example.professionalhomework.utils.Languages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val interactor: Interactor<AppState>) : BaseViewModel<AppState>() {

    private var languageCode: String = Languages.en_US.name

    override fun getData(word: String) {
        liveDataForViewToObserve.value = AppState.Loading(null)

        viewModelScope.launch(Dispatchers.IO) {
            liveDataForViewToObserve.postValue(
                interactor.getWord(
                    word = word,
                    languageCode = languageCode
                )
            )
        }
    }

    fun subscribe() = liveDataForViewToObserve

    fun onLanguageChanged(languagePosition: Int) {
        languageCode = Languages.values()[languagePosition].name
    }

}