package com.example.professionalhomework.ui.fragments.home

import androidx.lifecycle.viewModelScope
import com.example.professionalhomework.data.model.AppState
import com.example.professionalhomework.ui.base.viewmodel.BaseViewModel
import com.example.professionalhomework.utils.Languages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val interactor: HomeInteractor
) : BaseViewModel<AppState>() {

    private var languageCode: String = Languages.en_US.name

    fun getData(word: String) {
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

    fun onLanguageChanged(languagePosition: Int) {
        languageCode = Languages.values()[languagePosition].name
    }

}