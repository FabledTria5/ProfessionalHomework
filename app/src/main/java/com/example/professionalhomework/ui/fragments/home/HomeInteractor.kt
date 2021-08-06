package com.example.professionalhomework.ui.fragments.home

import com.example.professionalhomework.data.model.AppState
import com.example.professionalhomework.data.repository.*

class HomeInteractor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
) {

    suspend fun getWord(word: String, languageCode: String): AppState {
        var data = localRepository.getWord(word = word)
        if (data == null) {
            return try {
                val response = remoteRepository.getData(languageCode = languageCode, query = word)
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