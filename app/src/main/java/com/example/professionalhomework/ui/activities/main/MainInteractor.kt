package com.example.professionalhomework.ui.activities.main

import androidx.room.rxjava3.EmptyResultSetException
import com.example.professionalhomework.data.model.AppState
import com.example.professionalhomework.data.repository.DataConverter
import com.example.professionalhomework.data.repository.DictionaryRepository
import com.example.professionalhomework.ui.interactor.Interactor
import com.example.professionalhomework.rx.Schedulers
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val remoteRepository: DictionaryRepository,
    private val localRepository: DictionaryRepository,
) : Interactor<AppState> {

    override fun getWord(word: String, languageCode: String): Single<out AppState> =
        localRepository.getWord(word).map { AppState.Success(it) }
            .onErrorResumeNext { error ->
                if (error is EmptyResultSetException) {
                    remoteRepository.getWord(languageCode, word).flatMap { response ->
                        localRepository.fetchWord(
                            DataConverter.convertToWord(word, response[0].phonetics[0].audio),
                            DataConverter.convertToMeanings(word, response[0])
                        ).map {
                            AppState.Success(it)
                        }
                    }
                } else {
                    Single.error(error)
                }
            }
}