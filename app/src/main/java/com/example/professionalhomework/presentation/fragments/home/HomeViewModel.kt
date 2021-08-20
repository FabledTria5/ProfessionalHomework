package com.example.professionalhomework.presentation.fragments.home

import androidx.lifecycle.viewModelScope
import com.example.domain.usecasase.SearchWordUseCase
import com.example.professionalhomework.presentation.base.viewmodel.BaseViewModel
import com.example.professionalhomework.presentation.entities.AppState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val searchWordUseCase: SearchWordUseCase
) : BaseViewModel<AppState>() {

    fun getData(word: String) {
        liveDataForViewToObserve.value = AppState.Loading(null)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                searchWordUseCase.invoke(word = word)?.let {
                    liveDataForViewToObserve.postValue(AppState.SearchWordSuccess(data = it))
                }
                    ?: liveDataForViewToObserve.postValue(
                        AppState.Error(error = RuntimeException("Empty result"))
                    )
            } catch (e: Exception) {
                liveDataForViewToObserve.postValue(AppState.Error(error = e))
            }
        }
    }

}