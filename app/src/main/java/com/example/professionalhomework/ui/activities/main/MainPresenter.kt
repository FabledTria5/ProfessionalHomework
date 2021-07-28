package com.example.professionalhomework.ui.activities.main

import com.example.professionalhomework.data.db.entities.Meaning
import com.example.professionalhomework.data.db.relations.WordWithMeanings
import com.example.professionalhomework.data.model.AppState
import com.example.professionalhomework.ui.adapters.rv.word.MeaningViewHolder
import com.example.professionalhomework.ui.adapters.rv.word.MeaningsPresenter
import com.example.professionalhomework.ui.interactor.Interactor
import com.example.professionalhomework.rx.Schedulers
import com.example.professionalhomework.utils.Languages
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import timber.log.Timber
import java.util.*

class MainPresenter @AssistedInject constructor(
    private val mainInteractor: Interactor<AppState>,
    private val schedulers: Schedulers
) : MvpPresenter<MainView>() {

    inner class MeaningsPresenterImpl : MeaningsPresenter {

        val meaningsList = mutableListOf<Meaning>()

        override fun bindView(view: MeaningViewHolder, position: Int) {
            meaningsList[position].apply {
                view.bindMeaning(meaning = definition, partOfSpeech = partOfSpeech)
            }
        }

        override fun getCount() = meaningsList.count()
    }

    private val disposables = CompositeDisposable()
    val meaningsPresenter = MeaningsPresenterImpl()

    private var languageCode = "en_US"
    private var wordPronunciation: String? = null

    fun onWordSubmitted(query: String) {
        disposables += mainInteractor.getWord(
            query.lowercase(Locale.getDefault()).trim(),
            languageCode
        )
            .observeOn(schedulers.main())
            .doOnSubscribe { viewState.updateState(AppState.Loading(null)) }
            .subscribeBy(
                onSuccess = (viewState::updateState),
                onError = { onGetWordError(AppState.Error(it)) }
            )
    }

    fun onGetWordError(appState: AppState.Error) {
        Timber.e(appState.error)
        viewState.showError()
    }

    fun onGetWordSuccess(wordInfo: WordWithMeanings) {
        wordPronunciation = wordInfo.word.pronunciation

        meaningsPresenter.meaningsList.clear()
        meaningsPresenter.meaningsList.addAll(wordInfo.meanings)

        viewState.updateWord(wordInfo.word)

        if (wordPronunciation.isNullOrEmpty()) viewState.enableAudio()
        else viewState.disableAudio()

        viewState.hideLoading()
    }

    fun onPlayClicked() = wordPronunciation?.let(viewState::playWord)

    fun onLanguageSelected(position: Int) {
        languageCode = Languages.values()[position].name
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}