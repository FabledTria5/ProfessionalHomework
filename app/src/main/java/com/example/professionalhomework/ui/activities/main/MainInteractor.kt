package com.example.professionalhomework.ui.activities.main

import com.example.professionalhomework.data.model.AppState
import com.example.professionalhomework.data.repository.DataConverter
import com.example.professionalhomework.data.repository.DictionaryRepository
import com.example.professionalhomework.data.repository.DictionaryRepositoryImpl
import com.example.professionalhomework.ui.interactor.Interactor

class MainInteractor(
    private val remoteRepository: DictionaryRepository,
    private val localRepository: DictionaryRepository,
) : Interactor<AppState> {

    override suspend fun getWord(word: String, languageCode: String): AppState {
        var data = localRepository.getWord(word = word)
        if (data == null) {
            return try {
                val response = remoteRepository.getWord(languageCode = languageCode, query = word)
                data = localRepository.fetchWord(
                    DataConverter.convertToWord(
                        word,
                        response[0].phonetics[0].audio
                    ),
                    DataConverter.convertToMeanings(word, response[0])
                )
                AppState.Success(data)
            } catch (e: Exception) {
                AppState.Error(e)
            }
        } else {
            return AppState.Success(data)
        }
    }

}