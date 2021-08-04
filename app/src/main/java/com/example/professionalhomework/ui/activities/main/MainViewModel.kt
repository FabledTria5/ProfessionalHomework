package com.example.professionalhomework.ui.activities.main

import androidx.lifecycle.LiveData
import com.example.professionalhomework.data.model.AppState
import com.example.professionalhomework.rx.Schedulers
import com.example.professionalhomework.ui.base.viewmodel.BaseViewModel
import com.example.professionalhomework.ui.interactor.Interactor
import com.example.professionalhomework.utils.Languages
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: Interactor<AppState>,
) : BaseViewModel<AppState>() {

    private var appState: AppState? = null
    private var languageCode: String = Languages.en_US.name

    override fun getData(word: String): LiveData<AppState> {
        compositeDisposable += interactor.getWord(word, languageCode)
            .subscribeOn(schedulers.background())
            .observeOn(schedulers.main())
            .doOnSubscribe { liveDataForViewToObserve.value = AppState.Loading(null) }
            .subscribeBy(
                onSuccess = {
                    appState = it
                    liveDataForViewToObserve.value = appState
                },
                onError = { liveDataForViewToObserve.value = AppState.Error(it) }
            )
        return super.getData(word)
    }

    fun subscribe() = liveDataForViewToObserve

    fun onLanguageChanged(languagePosition: Int) {
        languageCode = Languages.values()[languagePosition].name
    }

}