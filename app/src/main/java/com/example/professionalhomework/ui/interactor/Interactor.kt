package com.example.professionalhomework.ui.interactor

import io.reactivex.rxjava3.core.Single

interface Interactor<T> {
    fun getWord(word: String, languageCode: String): Single<out T>
}