package com.example.professionalhomework.ui.fragments.home

import com.example.professionalhomework.data.model.AppState
import com.example.professionalhomework.data.repository.DataMapper
import com.example.professionalhomework.data.repository.LocalRepository
import com.example.professionalhomework.data.repository.RemoteRepository

class HomeInteractor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
) {

    suspend fun getWord(word: String): AppState {
        var data = localRepository.getWord(word = word)
        return if (data == null) {
            try {
                val response = remoteRepository.getData(query = word)
                data =  localRepository.fetchWord(
                    DataMapper.mapResponseToWord(response[0]),
                    DataMapper.mapResponseToSynonyms(response)
                )
                AppState.Success(data)
            } catch (e: Exception) {
                AppState.Error(e)
            }
        } else {
            AppState.Success(data)
        }
    }
}