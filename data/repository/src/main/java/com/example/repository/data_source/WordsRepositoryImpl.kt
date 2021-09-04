package com.example.repository.data_source

import com.db.DictionaryDatabase
import com.db.models.SynonymEntity
import com.db.models.WordEntity
import com.example.domain.models.WordItem
import com.example.domain.repository.WordsRepository
import com.example.network.DictionaryApi
import com.example.repository.mappers.toDomain
import com.example.repository.mappers.toSynonyms
import com.example.repository.mappers.toWord

class WordsRepositoryImpl(
    private val dictionaryApi: DictionaryApi,
    db: DictionaryDatabase
) : WordsRepository {

    private val dao = db.wordsDao()

    override suspend fun getAllWords() = dao.getAllWords().toDomain()

    override suspend fun fetchWord(word: String): WordItem? {
        return when (val data = dao.getWordWithSynonyms(word = word)) {
            null -> {
                val response = dictionaryApi.searchWord(word)
                if (response.size == 0) return null
                saveWord(response[0].toWord(word = word), response.toSynonyms(word = word))
                dao.getWordWithSynonyms(word = word)?.toDomain()
            }
            else -> data.toDomain()
        }
    }

    private suspend fun saveWord(wordEntity: WordEntity, synonymEntity: List<SynonymEntity>) {
        dao.insertWord(word = wordEntity)
        dao.insertSynonyms(synonyms = synonymEntity)
    }
}
