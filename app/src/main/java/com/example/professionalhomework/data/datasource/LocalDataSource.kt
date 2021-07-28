package com.example.professionalhomework.data.datasource

import com.example.professionalhomework.data.db.entities.Meaning
import com.example.professionalhomework.data.db.entities.Word
import com.example.professionalhomework.data.db.relations.WordWithMeanings
import io.reactivex.rxjava3.core.Single

interface LocalDataSource {
    fun getData(word: String): Single<WordWithMeanings>
    fun fetchData(word: Word, meanings: List<Meaning>): Single<WordWithMeanings>
}