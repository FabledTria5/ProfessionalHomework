package com.example.historyfeature.ui

import androidx.lifecycle.viewModelScope
import com.example.domain.usecasase.LoadHistoryUseCase
import com.example.professionalhomework.presentation.entities.AppState
import com.example.professionalhomework.presentation.base.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class HistoryViewModel(private val loadHistoryUseCase: LoadHistoryUseCase) :
    BaseViewModel<AppState>() {

    fun getData() {
        liveDataForViewToObserve.value = AppState.Loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                liveDataForViewToObserve.postValue(
                    AppState.LoadHistorySuccess(loadHistoryUseCase.invoke())
                )
            } catch (e: Exception) {
                liveDataForViewToObserve.postValue(AppState.Error(e))
                Timber.e(e)
            }
        }
    }

}