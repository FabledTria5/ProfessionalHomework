package com.example.professionalhomework.ui.fragments.history

import androidx.lifecycle.viewModelScope
import com.example.professionalhomework.data.model.AppState
import com.example.professionalhomework.ui.base.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(private val historyInteractor: HistoryInteractor) :
    BaseViewModel<AppState>() {

    fun getData() {
        liveDataForViewToObserve.value = AppState.Loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            liveDataForViewToObserve.postValue(historyInteractor.getAllWords())
        }
    }

}